/******************************************************************************
 *  Name:    
 *  NetID:   
 *  Precept: 
 *
 *  Partner Name:    
 *  Partner NetID:   
 *  Partner Precept: 
 * 
 ******************************************************************************/

Which partner is submitting the program files?

Programming Assignment 7: Markov Model

Hours to complete assignment (optional): 

/**********************************************************************
 * Describe the type parameters of your symbol table (i.e., what is   *
 * the key type and what is the value type). How did you use the      *
 * symbol table to implement the random() method.                     *
 **********************************************************************/
I used two symbol tables for the project. The first one used a substring as a key and mapped it to the number of times it occurred in the seed string.
The second mapped the same substring to a 130 element long array which documented how many times each of the 128 possible ASCII characters succeeded it in the original string. The last two 
indices contained the number of times it occurred (I used this to fill the first table) and at what index along the string the initializing loop left off.

I fed the array assosciated with a kgram into a StdRandom.discrete function, which returned the requied probabilities.


/**********************************************************************
 * The main() method we provide in the checklist does not test your   *
 * random() method. Describe how you tested it.                       *
 **********************************************************************/
I played around with the function by generating random characters separately after checking with the given main.


/**********************************************************************
 * Paste two of your favorite, not too long, output fragments here.   *
 * In addition to the pseudo-random text, indicate the order of your  *
 * model and what text file you used.                                 *
 **********************************************************************/
obal,   sodvd le httrhce een yssmounzambctd t .tarle  C aOil tlfftsl htai!  daidh  orhnIe  thn y H,s (bible.txt, order 0)

s Fables of
the Red Sea."


The Governor the way you yesterday, and found and wagged the Horse

IN THE WINTERTIME, when I turn of a Hare, scared by excellent
pillars on your best debate.  J

(aesop.txt, order 6)




/**********************************************************************
 *  Did you receive help from classmates, past COS 126 students, or
 *  anyone else? If so, please list their names.  ("A Sunday lab TA"
 *  or "Office hours on Thursday" is ok if you don't know their name.)
 **********************************************************************/

Yes or no?
No



/**********************************************************************
 *  Did you encounter any serious problems? If so, please describe.
 **********************************************************************/

Yes or no?




/**********************************************************************
 *  List any other comments here.                                     *
 **********************************************************************/

