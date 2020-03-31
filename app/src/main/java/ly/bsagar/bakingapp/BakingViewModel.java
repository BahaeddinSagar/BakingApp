package ly.bsagar.bakingapp;

import androidx.lifecycle.ViewModel;

import ly.bsagar.bakingapp.POJO.Step;

public class BakingViewModel extends ViewModel {
    String recipieID;
    String recipieName;
    Step step;

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }

    public String getRecipieName() {
        return recipieName;
    }

    public void setRecipieName(String recipieName) {
        this.recipieName = recipieName;
    }

    public String getRecipieID() {
        return recipieID;
    }

    public void setRecipieID(String recipieID) {
        this.recipieID = recipieID;
    }
}
