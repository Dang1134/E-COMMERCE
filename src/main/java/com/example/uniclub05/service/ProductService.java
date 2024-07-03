package com.example.uniclub05.service;

import com.example.uniclub05.dto.ProductDTO;
import com.example.uniclub05.entity.ProductDetailEntity;
import com.example.uniclub05.entity.ProductEntity;
import com.example.uniclub05.entity.ProductImageEntity;
import com.example.uniclub05.entity.key.ProductDetailID;
import com.example.uniclub05.exception.InsertProductException;
import com.example.uniclub05.exception.SaveFileException;
import com.example.uniclub05.payload.request.InsertProductRequest;
import com.example.uniclub05.repository.ProductDetailRepository;
import com.example.uniclub05.repository.ProductImageRepository;
import com.example.uniclub05.repository.ProductRepository;
import com.example.uniclub05.service.imp.FileServiceImp;
import com.example.uniclub05.service.imp.ProductServiceImp;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service

public class ProductService implements ProductServiceImp {
    @Autowired
    private FileServiceImp fileServiceImp ;
    @Autowired
    private ProductRepository productRepository ;
    @Autowired
    private ProductImageRepository productImageRepository ;

    @Autowired
    private ProductDetailRepository productDetailRepository ;


    @Autowired
    private RedisTemplate redisTemplate;
    @Transactional

    @Override
    public boolean insertProduct(InsertProductRequest request) {

       boolean isCopySuccess = fileServiceImp.saveFile(request.getFile());
       try {
           if (isCopySuccess){
               ProductEntity productEntity = new ProductEntity();
               productEntity.setDesc(request.getDesc());
               productEntity.setName(request.getName());
               productEntity.setPrice(request.getPrice());

               ProductEntity productSaved = productRepository.save(productEntity);

               ProductImageEntity productImageEntity = new ProductImageEntity() ;
               productImageEntity.setName(request.getFile().getOriginalFilename());
               productImageEntity.setProduct(productSaved);
               productImageRepository.save(productImageEntity);

               ProductDetailID productDetailID = new ProductDetailID();
               productDetailID.setIdProduct(productSaved.getId());
               productDetailID.setIdColor(request.getIdColor());
               productDetailID.setIdSize(request.getIdSize());

               ProductDetailEntity productDetailEntity = new ProductDetailEntity();
               productDetailEntity.setId(productDetailID);
               productDetailEntity.setQuantity(request.getQuantity());
               productDetailEntity.setPrice(request.getPricePlus());

               productDetailRepository.save(productDetailEntity);

           }
       } catch (Exception e){
           throw new InsertProductException(e.getMessage());

       }
        return false;
    }
//    @Cacheable("product")

    @Override
    public List<ProductDTO> getAll() {
        ObjectMapper objectMapper = new ObjectMapper();
        List<ProductDTO> listDTO = new ArrayList<>();
        if (redisTemplate.hasKey("product")){
            String dataProductCached = redisTemplate.opsForValue().get("product").toString();
            try{
                listDTO = objectMapper.readValue(dataProductCached, new TypeReference<List<ProductDTO>>() {
                });
            }
            catch (Exception e){
                throw new RuntimeException("error" + e.getMessage());
            }

        }else {
            System.out.println("kiemtra Product");
            List<ProductEntity> listProductEntity = productRepository.findAll();
            List<ProductDTO> productDTOList = new ArrayList<>();
            listProductEntity.forEach(item -> {
                ProductDTO productDTO = new ProductDTO();
                productDTO.setName(item.getName());
                productDTO.setPrice(item.getPrice());

                List<String> images = new ArrayList<>();
                item.getProductImages().forEach(itemImage ->{
                    images.add("http://localhost:9095/file/" + itemImage.getName());
                });
                productDTO.setImages(images);
                productDTOList.add(productDTO);
            });
                listDTO.addAll(productDTOList);
            try {
                String dataProduct = objectMapper.writeValueAsString(listDTO);
                redisTemplate.opsForValue().set("product" ,dataProduct);
            }catch (Exception e){
                throw new RuntimeException("errror");
            }

        }
        return  listDTO;




    }
    
}
