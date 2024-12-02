package Y2019.IntcodeComputerChallenge;

public class IntcodeReport {
    boolean needsInput;
    Long outputValue;
    boolean haltFlag;

    public IntcodeReport(){
        this.needsInput = false;
        this.outputValue = null;
        this.haltFlag = false;
    }

    public IntcodeReport(boolean needsInput, Long outputValue, boolean haltFlag){
        this.needsInput = needsInput;
        this.outputValue = outputValue;
        this.haltFlag = haltFlag;
    }

    @Override
    public String toString(){
        return String.valueOf(needsInput) + ","
                + String.valueOf(outputValue) + ","
                + String.valueOf(haltFlag);
    }
}
