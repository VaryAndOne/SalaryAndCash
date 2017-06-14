package com.vary.salaryandcash.mvp.model;

public class SalariesResponse {
    private String product;
    private String releaseDate;
    private double version;
    private SalariesResponseSalaries[] Salaries;

    public String getProduct() {
        return this.product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getReleaseDate() {
        return this.releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public double getVersion() {
        return this.version;
    }

    public void setVersion(double version) {
        this.version = version;
    }

    public SalariesResponseSalaries[] getSalaries() {
        return this.Salaries;
    }

    public void setSalaries(SalariesResponseSalaries[] Salaries) {
        this.Salaries = Salaries;
    }
}
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