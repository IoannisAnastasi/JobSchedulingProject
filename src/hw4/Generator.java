package hw4;

import java.util.Random;

public class Generator extends Order {
	private int portions;
	
	public Generator()
	{
		super();
		portions=generateNumOfPortions();
	}

	public  void  generateEachPortion()
    {
    	for(int i=0; i<portions; i++)
    	{
    		double randomNum=Math.random()*100;
    		if(randomNum>=0 && randomNum<=25)
    			setNpp((getNpp()+1));
    		else if(randomNum>25 && randomNum<=50)
    			setNpc((getNpc()+1));
    		else if(randomNum>50 && randomNum<=75)
    			setNps((getNps()+1));
    		else
    			setNpm((getNpm()+1));
    	}
    }
	
	public  void  generateFries()
    {
    	for(int i=0; i<portions; i++)
    	{
    		double randomNum=Math.random()*100;
    		if(randomNum>=0 && randomNum<=60)
    			setFries((getFries()+1));
    		else if(randomNum>60 && randomNum<=95)
    		{}
    		else
    			setFries((getFries()+2));
    	}
    }
	
	public void generateTimeOfOrder()
    {
    	Random random = new Random();
    	setTorder((int)(60*random.nextGaussian()+180));
    }
    
    public void generateRequestedTime()
    {
    	if(portions<=10)
    	 setTdelReq((int)(Math.random()*30)+30);
    	else
       	 setTdelReq((int)(Math.random()*30)+60);
    }
    	 // Generate the number of portions based on probabilities
        private  int generateNumOfPortions() {
            // Define the probabilities for different portions
        	double[] probabilities= {0.20, 0.35, 0.10, 0.20, 0.15};
        	
            // Total probability sum
            double totalProbability = 100;

            // Choose a random number between 0 and totalProbability
            double randomNum = Math.random() * totalProbability;

            // Find which portion count corresponds to the random number
            double cumulativeProbability = 0;
            int portionCount = 1; // Default to 1 portion
            for (int i = 0; i < probabilities.length; i++) {
                cumulativeProbability += probabilities[i];
                if (randomNum < cumulativeProbability) {
                    portionCount = i + 1; // i + 1 corresponds to portion count
                    break;
                }
            }

            // If the selected portion count is in the last category, choose a random count between 5 and 20
            if (portionCount == probabilities.length) {
                portionCount = (int) (Math.random() * 16) + 5; // Random number between 5 and 20
            }

            return portionCount;
        }
        
        public String toString()
        {
        	return super.toString();
        }

    }

