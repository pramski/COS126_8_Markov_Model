import java.util.Arrays;
/************************************************************************************************************************************************
 Here we will create the Markov Model data type, that we will use subsequently in a client program. Our instance variables
 consist of order, an integer representing thr order of the model, two Symbol Tables that associate every substring (of length order)
 two the number of times it occurs in a given string, and the characters that follow and them (and the number of times each character occurs)
 respectively. We also have an integer constant ASCII, with value 128 (on for each ascii character this becomes useful in the constructor)
 ************************************************************************************************************************************************/
public class MarkovModel {
    public int order;
    public int ASCII=128;
    public ST<String, Integer> lookUp;
    public ST<String, int[]> lookUp2;
    /**@param text is the string that serves as the base for the Markov Model
     * @param k is the order of the Markov Model
     * We append the initial k-gram to the end of the text to make it circular.
     * We keep two symbol tables and iterate through the string, k characters at a time
     *
     * In an iteration, if we encounter a k-gram for the first time, we put it in both tables and associate it to an integer in lookUp and a ASCII+2 length array in lookUp2
     * Every subsequent time we encounter the k-gram in the string, we add 1 to its lookUp value, and we add one to the index representing the integer value of its succeeding
     * ASCII character. The last two indices contain the position of the substring's last occurrence(so that we know where to resume our search form the next time we encounter it
     * and the number of times it occurs respectively */
    public MarkovModel(String text, int k){
        lookUp = new ST<>();
        lookUp2 = new ST<>();
        order = k;
        String circText = text+text.substring(0, k);
        for (int i = 0; i <text.length(); i+=1){
            String s=circText.substring(i,i+k);
            if(lookUp2.contains(s)&&lookUp.contains(s)){
                int[] occ =lookUp2.get(s);
                int pos=circText.indexOf(s,occ[ASCII]);
                if(pos!=-1){
                    char succ=circText.charAt(pos+k);
                    occ[succ]+=1;
                    occ[ASCII]=pos+1;
                    occ[ASCII+1]+=1;
                    lookUp.put(s,occ[ASCII+1]);
                }
            }
            else {
                lookUp2.put(s, new int[ASCII+2]);
                int[] occ= lookUp2.get(s);
                int pos=circText.indexOf(s,occ[ASCII]);
                if(pos!=-1){
                    char succ=circText.charAt(pos+k);
                    occ[succ]+=1;
                    occ[ASCII]=pos+1;
                    occ[ASCII+1]+=1;
                    lookUp.put(s,occ[ASCII+1]);
                }
            }

        }

    }
/**@return the order of the Markov Model*/
    public int order(){
        return order;
    }
    /**@return the string value of the Markov Model.
     * This is a string representation of a Markov Model object. It consists of a tabular column of salts, with all the k-grams laid out in one column, and each of them
     * has to their right, every character that succeeds them, followed by the number of times it does so*/
    public String toString(){
        StringBuilder result= new StringBuilder();
        for(String key:lookUp2.keys()){
            result.append(key).append(":");
            for(int i=0;i<lookUp2.get(key).length-2;i++){
                if(lookUp2.get(key)[i]!=0){
                    result.append(" ").append((char)lookUp2.get(key)[i]).append(lookUp2.get(key)[i]);
                }
            }
            result.append("\n");
        }
        return result.toString();
    }
    /**@param kgram is any k-gram
     * @return the number of times it occurs in the string
     * this is done by simply returning the value associated with the k-gram in lookUp
     * If it does not occur, it returns 0, and if it is not of length k, the method throws an IllegalArgumentException */
    public int freq(String kgram){
        if(lookUp.contains(kgram)&&kgram.length()==order){
            return lookUp.get(kgram);
        } else if (!lookUp.contains(kgram)) {
            return 0;
        } else{
            throw new IllegalArgumentException("");
        }
    }
    /**@param c the character succeeding a k-gram
     * @param kgram the aforementioned k-gram
     * This method uses the value associated with entry (int) c of the array mapped to kgram in lookUp2
     * If this kgram does not exist, then an IllegalArgumentException is thrown
     * If it is inalid for any other reason, IllegalArgumentException is thrown again*/
    public int freq(String kgram, char c){
        if(kgram.length()==order){
            return lookUp2.get(kgram)[c];
        } else if (!lookUp2.contains(kgram)){
            throw new IllegalArgumentException("");
        }
        else
        {
            throw new IllegalArgumentException("");
        }
    }
    /**@param kgram, a kgram
     * @return a random character based on the probability derived from the number of times that character occurred in the entire string
     * This is done by copying the kgrams associated array of successor characters into an ASCII length array and running the StdRandom.discrete method on the new array*/
    public char random(String kgram){
        int[] probs=new int[ASCII];
        Arrays.fill(probs,0);
        int[] occurrence=lookUp2.get(kgram);
        System.arraycopy(occurrence,0,probs,0,ASCII);
        int pick=StdRandom.discrete(probs);
        return (char)pick;
    }
    /*Testing the output*/
    public static void main(String[]args){
        String text1= "gagggagaggcgagaaa";
        MarkovModel model1 = new MarkovModel(text1, 2);
        StdOut.println(model1.random("ga"));

        String text3 = "one fish two fish red fish blue fish";
        MarkovModel model3 = new MarkovModel(text3, 4);
        StdOut.println("freq(\"ish \", 'r') = " + model3.freq("ish ", 'r'));
        StdOut.println("freq(\"ish \", 'x') = " + model3.freq("ish ", 'x'));
        StdOut.println("freq(\"ish \")      = " + model3.freq("ish "));
        StdOut.println("freq(\"tuna\")      = " + model3.freq("tuna"));
    }
}
