package conversion.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Conversion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String originalAmountName;

    private String originalAmount;

    private String targetAmountName;

    private String targetAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOriginalAmountName() {
        return originalAmountName;
    }

    public void setOriginalAmountName(String originalAmountName) {
        this.originalAmountName = originalAmountName;
    }

    public String getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(String originalAmount) {
        this.originalAmount = originalAmount;
    }

    public String getTargetAmountName() {
        return targetAmountName;
    }

    public void setTargetAmountName(String targetAmountName) {
        this.targetAmountName = targetAmountName;
    }

    public String getTargetAmount() {
        return targetAmount;
    }

    public void setTargetAmount(String targetAmount) {
        this.targetAmount = targetAmount;
    }


}
