package com.forestry.bean;

import javax.validation.constraints.NotBlank;

public class BoardCert {
    @NotBlank
    private String amount;

    @NotBlank
    private String noticePic;

    @NotBlank
    private String contractPic;

    @NotBlank
    private String declarationPic;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getNoticePic() {
        return noticePic;
    }

    public void setNoticePic(String noticePic) {
        this.noticePic = noticePic;
    }

    public String getContractPic() {
        return contractPic;
    }

    public void setContractPic(String contractPic) {
        this.contractPic = contractPic;
    }

    public String getDeclarationPic() {
        return declarationPic;
    }

    public void setDeclarationPic(String declarationPic) {
        this.declarationPic = declarationPic;
    }
}
