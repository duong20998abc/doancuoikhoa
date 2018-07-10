package application.data.service;


import application.data.model.New;
import application.data.model.PaginableItemList;
import application.data.model.Product;
import application.data.repository.NewRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewService {
    private static final Logger logger = LogManager.getLogger(NewService.class);

    @Autowired
    private NewRepository newRepository;

    public void addNewNew(New neww) {
        newRepository.save(neww);

    }

    @Transactional
    public void addNewListNews(List<New> listNews) {
        newRepository.save(listNews);
    }

    public long getTotalNews() {
        return newRepository.getTotalNews();
    }

    public PaginableItemList<New> getListNews(int pageSize, int pageNumber) {
        PaginableItemList<New> paginableItemList = new PaginableItemList<>();
        paginableItemList.setPageSize(pageSize);
        paginableItemList.setPageNumber(pageNumber);

        Page<New> pages = newRepository.findAll(new PageRequest(pageNumber,pageSize));
        paginableItemList.setTotalNews(pages.getTotalElements());
        paginableItemList.setListData(pages.getContent());
        return paginableItemList;
    }

    public New findOne(int newid) {
        return newRepository.findOne(newid);
    }

    public boolean updateNew(New neww) {
        try {
            newRepository.save(neww);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public boolean deleteNew(int newid) {
        try {
            newRepository.delete(newid);
            return true;
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return false;
    }

    public ArrayList<New> getAll(){
        return newRepository.getAll();
    }
}
