package com.forestry.bean;

import javax.validation.constraints.NotBlank;

public class WoodCert {
    @NotBlank
    private String amount;

    @NotBlank
    private String noticePic;

    @NotBlank
    private String ladingPic;

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

    public String getLadingPic() {
        return ladingPic;
    }

    public void setLadingPic(String ladingPic) {
        this.ladingPic = ladingPic;
    }

    public String getDeclarationPic() {
        return declarationPic;
    }

    public void setDeclarationPic(String declarationPic) {
        this.declarationPic = declarationPic;
    }
}
