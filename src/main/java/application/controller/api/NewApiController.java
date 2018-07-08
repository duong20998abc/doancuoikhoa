package application.controller.api;

import application.constant.Constant;
import application.data.model.New;
import application.data.model.Product;
import application.data.service.CategoryService;
import application.data.service.NewService;
import application.data.service.ProductService;
import application.model.*;
import application.viewmodel.common.ProductVM;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 * Created by ManhNguyen on 3/1/18.
 */
@RestController
@RequestMapping("/api/new")
public class NewApiController {

        @Autowired
        private NewService newService;



        private String[] images = {
                "https://images-na.ssl-images-amazon.com/images/I/519rVW4jTIL._SL500_SS135_.jpg",
                "https://images-na.ssl-images-amazon.com/images/I/51JJ0e-i2yL._SL500_SS135_.jpg",
                "https://images-na.ssl-images-amazon.com/images/I/517Q0wwZUXL._SL500_SS135_.jpg",
                "https://images-na.ssl-images-amazon.com/images/I/41odx4vtkyL._SL500_SS135_.jpg",
                "https://images-na.ssl-images-amazon.com/images/I/41KCisxFTwL._SL500_SS135_.jpg"
        };

        @GetMapping("/detail/{newId}")
        public BaseApiResult detailNew(@PathVariable int newId) {
                DataApiResult result = new DataApiResult();
                try{
                        New existNew = newService.findOne(newId);
                        if(existNew == null) {
                                result.setSuccess(false);
                                result.setMessage("Can't find this new");
                        } else {
                                result.setSuccess(true);
                                ModelMapper modelMapper = new ModelMapper();
                                NewDetailModel newDetailModel = modelMapper.map(existNew , NewDetailModel.class);
                                result.setData(newDetailModel);
                        }
                } catch (Exception e) {
                        result.setSuccess(false);
                        result.setMessage(e.getMessage());
                }
                return result;
        }

        @PostMapping("/fake-news")
        public BaseApiResult fakeNews() {
                ArrayList<New> listNews= new ArrayList<>();
                Random random = new Random();
                BaseApiResult result = new BaseApiResult();

                for(int i = 1; i <= 100; ++i) {
                        New n = new New();
                        n.setCreatedDate(new Date());
                        n.setTitle("New " + i);
                        n.setContent("Description for product " + i);
                        n.setImg(images[random.nextInt(images.length)]);

                        listNews.add(n);
                }

                newService.addNewListNews(listNews);
                result.setSuccess(true);
                result.setMessage("Done");
                return result;
        }

        @PostMapping("/create-new")
        public BaseApiResult createNew(@RequestBody NewDataModel neww) {
                DataApiResult result = new DataApiResult();

                try {
                        if(!"".equals(neww.getTitle()) && !"".equals(neww.getContent()) && !"".equals(neww.getImg())) {
                                ModelMapper modelMapper = new ModelMapper();
                                New newEntity = modelMapper.map(neww, New.class);
                                newService.addNewNew(newEntity);
                                result.setSuccess(true);
                                result.setMessage("Save new successfully: " + newEntity.getNewId());
                                result.setData(newEntity.getNewId());
                        } else {
                                result.setSuccess(false);
                                result.setMessage("Invalid model");
                        }
                } catch (Exception e) {
                        result.setSuccess(false);
                        result.setMessage(e.getMessage());
                }

                return result;
        }

        @PostMapping("/update-new/{newId}")
        public BaseApiResult updateNew(@PathVariable int newId, @RequestBody NewDataModel neww) {
                BaseApiResult result = new BaseApiResult();

                try {
                        if(!"".equals(neww.getTitle()) && !"".equals(neww.getContent()) && !"".equals(neww.getImg())) {
                                // check existed new
                                New existNew = newService.findOne(newId);
                                if(existNew == null) {
                                        result.setSuccess(false);
                                        result.setMessage("Invalid model");
                                } else {
                                        existNew.setImg(neww.getImg());
                                        existNew.setTitle(neww.getTitle());
                                        existNew.setCreatedDate(neww.getCreatedDate());
                                        existNew.setContent(neww.getContent());
                                        newService.updateNew(existNew);
                                        result.setSuccess(true);
                                        result.setMessage("Update new successfully");
                                }
                        } else {
                                result.setSuccess(false);
                                result.setMessage("Invalid model");
                        }
                } catch (Exception e) {
                        result.setSuccess(false);
                        result.setMessage(e.getMessage());
                }

                return result;
        }

        @PostMapping("/delete-new")
        public BaseApiResult deleteNew(@RequestBody NewDeleteDataModel neww) {
                BaseApiResult result = new BaseApiResult();

                try {
                        if(newService.deleteNew(neww.getNewId())) {
                                result.setSuccess(true);
                                result.setMessage("Delete new successfully");
                        }
                } catch (Exception e) {
                        result.setSuccess(false);
                        result.setMessage(e.getMessage());
                }

                return result;
        }


        @GetMapping("/getall")
        public BaseApiResult getAll(){
                DataApiResult result = new DataApiResult();
                ModelMapper modelMapper = new ModelMapper();

                try {
                        ArrayList<New> news = new ArrayList<>();
                        ArrayList<NewDetailModel> newDetailModels = new ArrayList<>();
                        news = newService.getAll();
                        for(New n : news){
                                newDetailModels.add(modelMapper.map(n,NewDetailModel.class));
                        }
                        result.setMessage("success");
                        result.setData(newDetailModels);
                        result.setSuccess(true);
                } catch (Exception e) {
                        result.setSuccess(false);
                        result.setData(null);
                        result.setMessage(e.getMessage());
                }
                return result;
        }

}
