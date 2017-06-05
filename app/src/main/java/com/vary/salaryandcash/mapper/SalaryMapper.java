package com.vary.salaryandcash.mapper;

/**
 * Created by Administrator on 2017-06-05.
 */

import com.vary.salaryandcash.mvp.model.SalariesResponse;
import com.vary.salaryandcash.mvp.model.SalariesResponseSalaries;
import com.vary.salaryandcash.mvp.model.Salary;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class SalaryMapper {

    @Inject
    public SalaryMapper() {
    }

    public List<Salary> mapCakes(SalariesResponse response) {
        List<Salary> cakeList = new ArrayList<>();

        if (response != null) {
            SalariesResponseSalaries[] responseCakes = response.getSalaries();
            if (responseCakes != null) {
                for (SalariesResponseSalaries cake : responseCakes) {
                    Salary myCake = new Salary();
                    myCake.setId(cake.getId());
                    myCake.setTitle(cake.getTitle());
                    myCake.setDetailDescription(cake.getDetailDescription());
                    myCake.setPreviewDescription(cake.getPreviewDescription());
                    myCake.setMicroVideo(cake.getMicroVideo());
                    cakeList.add(myCake);
                }
            }
        }
        return cakeList;
    }


}
