package com.forestry.bean;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class Company {
    @NotNull
    private int id;

    @NotBlank
    private String name;

    @NotBlank
    private String code;

    @NotBlank
    private String corporation;

    @NotBlank
    private String phone;

    @NotBlank
    private String address;

    @NotBlank
    private String store;

    @NotBlank
    private String companyType;

    @NotBlank
    private String source;

    @NotNull
    @Digits(integer = 1, fraction = 0)
    private int outCityCompany;

    @NotNull
    private String outCityCompanyName;

    @NotBlank
    private String kind;

    @NotNull
    private String saw;

    @NotNull
    private String sawOutput;

    @NotNull
    private String other;

    @NotNull
    private String otherOutput;

    @NotNull
    private String product;

    @NotNull
    private String saleArea;

    @NotNull
    private String saleMount;

    @NotNull
    private String remark;

    @NotBlank
    private String licencePic;

    @NotBlank
    private String cardFrontPic;

    @NotBlank
    private String cardOppositePic;

    @NotBlank
    private String notificationPic;

    @NotBlank
    private String commitPic;

    private String create_time;

    private String last_modify_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCorporation() {
        return corporation;
    }

    public void setCorporation(String corporation) {
        this.corporation = corporation;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public int getOutCityCompany() {
        return outCityCompany;
    }

    public void setOutCityCompany(int outCityCompany) {
        this.outCityCompany = outCityCompany;
    }

    public String getOutCityCompanyName() {
        return outCityCompanyName;
    }

    public void setOutCityCompanyName(String outCityCompanyName) {
        this.outCityCompanyName = outCityCompanyName;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getSaw() {
        return saw;
    }

    public void setSaw(String saw) {
        this.saw = saw;
    }

    public String getSawOutput() {
        return sawOutput;
    }

    public void setSawOutput(String sawOutput) {
        this.sawOutput = sawOutput;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getOtherOutput() {
        return otherOutput;
    }

    public void setOtherOutput(String otherOutput) {
        this.otherOutput = otherOutput;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getSaleArea() {
        return saleArea;
    }

    public void setSaleArea(String saleArea) {
        this.saleArea = saleArea;
    }

    public String getSaleMount() {
        return saleMount;
    }

    public void setSaleMount(String saleMount) {
        this.saleMount = saleMount;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLicencePic() {
        return licencePic;
    }

    public void setLicencePic(String licencePic) {
        this.licencePic = licencePic;
    }

    public String getCardFrontPic() {
        return cardFrontPic;
    }

    public void setCardFrontPic(String cardFrontPic) {
        this.cardFrontPic = cardFrontPic;
    }

    public String getCardOppositePic() {
        return cardOppositePic;
    }

    public void setCardOppositePic(String cardOppositePic) {
        this.cardOppositePic = cardOppositePic;
    }

    public String getNotificationPic() {
        return notificationPic;
    }

    public void setNotificationPic(String notificationPic) {
        this.notificationPic = notificationPic;
    }

    public String getCommitPic() {
        return commitPic;
    }

    public void setCommitPic(String commitPic) {
        this.commitPic = commitPic;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }

    public String getLast_modify_time() {
        return last_modify_time;
    }

    public void setLast_modify_time(String last_modify_time) {
        this.last_modify_time = last_modify_time;
    }
}
