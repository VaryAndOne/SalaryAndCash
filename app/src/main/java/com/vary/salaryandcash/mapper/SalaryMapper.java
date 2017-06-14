package com.vary.salaryandcash.mapper;

/**
 * Created by
 *
 * 温家才
 * 性别 男 民族 汉
 * 生日1993年9月7日
 * 住址 吉林省农安县青山口乡柳条沟村乡约屯17组
 * 公民身份证号 220122199309077010
 *
 * on 2017-06-03.
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
                    myCake.setImage(cake.getImage());
                    cakeList.add(myCake);
                }
            }
        }
        return cakeList;
    }


}
