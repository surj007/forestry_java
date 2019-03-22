package com.forestry.bean;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class PlantCert {
    private int id;

    @NotNull
    private String producing_area;

    @NotNull
    private String processing_area;

    @NotBlank
    private String plant_name;

    @NotBlank
    private String variety;

    @NotBlank
    private String car_amount;

    @NotBlank
    private String every_car_amount;

    @NotNull
    private String packaging;

    @NotNull
    private String standard;

    @NotBlank
    private String receive_person;

    @NotNull
    private int receive_address_type;

    @NotBlank
    private String receive_address;

    @NotBlank
    private String phone;

    @NotBlank
    private String person_id;

    @NotBlank
    private String date_time;

    @NotNull
    private String apply_person;

    @NotBlank
    private String transport_person;

    @NotBlank
    private String report_number;

    @NotBlank
    private String car_number;

    private String picture_url;

    private String picture_location;

    private String picture_time;

    private int cid;

    private String windows;

    private int status;

    private String refuse_reason;

    private String check_person;

    private String approve_remark;

    private String create_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProducing_area() {
        return producing_area;
    }

    public void setProducing_area(String producing_area) {
        this.producing_area = producing_area;
    }

    public String getProcessing_area() {
        return processing_area;
    }

    public void setProcessing_area(String processing_area) {
        this.processing_area = processing_area;
    }

    public String getPlant_name() {
        return plant_name;
    }

    public void setPlant_name(String plant_name) {
        this.plant_name = plant_name;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getCar_amount() {
        return car_amount;
    }

    public void setCar_amount(String car_amount) {
        this.car_amount = car_amount;
    }

    public String getEvery_car_amount() {
        return every_car_amount;
    }

    public void setEvery_car_amount(String every_car_amount) {
        this.every_car_amount = every_car_amount;
    }

    public String getPackaging() {
        return packaging;
    }

    public void setPackaging(String packaging) {
        this.packaging = packaging;
    }

    public String getStandard() {
        return standard;
    }

    public void setStandard(String standard) {
        this.standard = standard;
    }

    public String getReceive_person() {
        return receive_person;
    }

    public void setReceive_person(String receive_person) {
        this.receive_person = receive_person;
    }

    public int getReceive_address_type() {
        return receive_address_type;
    }

    public void setReceive_address_type(int receive_address_type) {
        this.receive_address_type = receive_address_type;
    }

    public String getReceive_address() {
        return receive_address;
    }

    public void setReceive_address(String receive_address) {
        this.receive_address = receive_address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPerson_id() {
        return person_id;
    }

    public void setPerson_id(String person_id) {
        this.person_id = person_id;
    }

    public String getDate_time() {
        return date_time;
    }

    public void setDate_time(String date_time) {
        this.date_time = date_time;
    }

    public String getApply_person() {
        return apply_person;
    }

    public void setApply_person(String apply_person) {
        this.apply_person = apply_person;
    }

    public String getTransport_person() {
        return transport_person;
    }

    public void setTransport_person(String transport_person) {
        this.transport_person = transport_person;
    }

    public String getReport_number() {
        return report_number;
    }

    public void setReport_number(String report_number) {
        this.report_number = report_number;
    }

    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    public String getPicture_location() {
        return picture_location;
    }

    public void setPicture_location(String picture_location) {
        this.picture_location = picture_location;
    }

    public String getPicture_time() {
        return picture_time;
    }

    public void setPicture_time(String picture_time) {
        this.picture_time = picture_time;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public String getWindows() {
        return windows;
    }

    public void setWindows(String windows) {
        this.windows = windows;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getRefuse_reason() {
        return refuse_reason;
    }

    public void setRefuse_reason(String refuse_reason) {
        this.refuse_reason = refuse_reason;
    }

    public String getCheck_person() {
        return check_person;
    }

    public void setCheck_person(String check_person) {
        this.check_person = check_person;
    }

    public String getApprove_remark() {
        return approve_remark;
    }

    public void setApprove_remark(String approve_remark) {
        this.approve_remark = approve_remark;
    }

    public String getCreate_time() {
        return create_time;
    }

    public void setCreate_time(String create_time) {
        this.create_time = create_time;
    }
}
