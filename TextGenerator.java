/***********************************************************************************************************************
 Here is our client program for the Markov Model data type we just designed.
 ***********************************************************************************************************************/
public class TextGenerator {
    /* We take two arguments: k, the order of the Markov model and T, the number of characters we want to print
     * To assemble our input text into a comprehensible form, we read all lines into standard input, and then assemble the lines into a Stringuilder object to account for
     * the spaces between words and paragraphs */
    public static void main(String[] args) {
        int k=Integer.parseInt(args[0]);
        int T=Integer.parseInt(args[1]);
        StringBuilder tex= new StringBuilder();
        String[] text=StdIn.readAllLines();
        for(String str:text) {
            tex.append(str);
            tex.append("\n");

        }
        /*Our seed string for the MarkovModel object is the first k-substring of our input. Then for T-k repeptitions, we generate a random character(which we print),
        remove the first character from our seed, append the generated character and then repeat. This is done with another StringBuilder object.*/
        String seed=tex.substring(0,k);
        MarkovModel generator=new MarkovModel(tex.toString(),k);
        for(int i=0;i<T-k;i++){
            char succ=generator.random(seed);
            StdOut.print(succ);
            StringBuilder seedBuild=new StringBuilder(seed);
            seedBuild.append(succ);
            seedBuild.deleteCharAt(0);
            seed=seedBuild.toString();
        }
    }
}
