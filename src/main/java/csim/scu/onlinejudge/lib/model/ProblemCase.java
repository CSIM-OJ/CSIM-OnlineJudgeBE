package csim.scu.onlinejudge.lib.model;

public class ProblemCase {

    private String input;
    private String output;

    public ProblemCase(String input, String output) {
        this.input = input;
        this.output = output;
    }

    public String getInputStr() {
        return input;
    }

    public String[] getInputArray() {
        String[] result = input.split(" ");
        return result;
    }

    public String getOutput() {
        return output;
    }
}
