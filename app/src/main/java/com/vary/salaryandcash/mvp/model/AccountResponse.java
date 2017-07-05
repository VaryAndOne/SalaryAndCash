package com.vary.salaryandcash.mvp.model;

public class AccountResponse {
    private String UniqueID;
    private String product;
    private String releaseDate;
    private double version;
    private AccountResponseSalaries[] Salaries;
    private String PassWord;

    public String getUniqueID() {
        return this.UniqueID;
    }

    public void setUniqueID(String UniqueID) {
        this.UniqueID = UniqueID;
    }

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

    public AccountResponseSalaries[] getSalaries() {
        return this.Salaries;
    }

    public void setSalaries(AccountResponseSalaries[] Salaries) {
        this.Salaries = Salaries;
    }

    public String getPassWord() {
        return this.PassWord;
    }

    public void setPassWord(String PassWord) {
        this.PassWord = PassWord;
    }
}
