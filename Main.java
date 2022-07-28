import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	
	public static int iterations = 0;
	public static int pSize = 1000;
	
	
	//Generates one Chromosome
	private static Chromosome generateChromosome(int size)
	{
		Chromosome chrom = new Chromosome(size);
		 ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 1; i <= size ;++i)
		{
			list.add(i);
		}
			Collections.shuffle(list);
		for(int i = 0; i < size ;++i)
		{
			chrom.setIndex(i,list.get(i));
		}
		return chrom;
	}
	
	
	// N-Queen problem solution
	public static Chromosome solveNQueens( Chromosome[] tPopulation,double mutationProb, int maxiter)
	{
		if(iterations > maxiter)
		{
			System.out.println("The algorithm needs more time to find a solution.");
			return null;
		}
		++iterations;
		int[] populationFittness = new int[tPopulation.length];
		int size0 = tPopulation.length;
		for(int i = 0; i < size0; ++i)
		{
			populationFittness[i] = countFittness(tPopulation[i]);
			if(populationFittness[i] == 0)
			{
				return tPopulation[i];
			}	
		}
		int[] fittnessToSort = new int[size0];
		for(int i = 0; i < populationFittness.length; ++i)
		{
			fittnessToSort[i] = populationFittness[i];
		}
		Arrays.sort(fittnessToSort);
		Chromosome[] totalPopulation  = new Chromosome[size0];
		for(int i = 0; i < size0; ++i)
		{
			for(int j = 0; j < size0; ++j)
			{
				if(populationFittness[j] == fittnessToSort[i])
				{
					totalPopulation[i] = tPopulation[j];
					populationFittness[j] = -5;
					break;
				}
			}
		}
		
		Chromosome[] children = new Chromosome[2];
		Chromosome[] newPopulation = new Chromosome[size0];
		for(int i = 0; i < size0 - 1; i += 2)
		{
			children = crossover(totalPopulation[i],totalPopulation[i+1]); 
			Chromosome a = mutation(children[0],mutationProb);
			Chromosome b = mutation(children[1],mutationProb);
			newPopulation[i] = mutation(a,0.3);
			newPopulation[i+1] = mutation(b,0.3);
		}
		return solveNQueens(newPopulation, mutationProb, maxiter);
	}
	
	
	//Counts the number of attacking queen pairs 
	private static int countFittness(Chromosome ch)
	{
		int repetitiveQueens1 = 0;
		int repetitiveQueens2 = 0;
		int length = ch.size();
		int[] diagonal1 = new int[length];
		int[] diagonal2 = new int[length];
		
		for(int i= 0; i < length; ++i)
		{
			diagonal1[i] = ch.getIndex(i) - (i + 1);
			diagonal2[i] = 1 + length - ch.getIndex(i) - (i +1);
		}
		Arrays.sort(diagonal1);
		Arrays.sort(diagonal2);
		for(int i= 1; i < length; ++i)
		{
			 if(diagonal1[i] == diagonal1[i-1]) 
				 ++repetitiveQueens1;
			 if(diagonal2[i] == diagonal2[i-1]) 
				 ++repetitiveQueens2;
		}
		int[] row = new int[length];
		for (int i = 0; i < length; i++){
		    {
		    	int val = ch.getIndex(i);
		    	row[val-1]++;
		    }
		  
		}
		
		int result = repetitiveQueens1 + repetitiveQueens2;
		return result;
	}
	
	
	// Generates two children from the given parents 
	private static Chromosome[] crossover(Chromosome parent1, Chromosome parent2)
	{
		int length = parent1.size();
		int max = length - 1;
		int min = 0;
		Chromosome child1 = new Chromosome(length);
		Chromosome child2 = new Chromosome(length);
		int point1 = (int)Math.floor(Math.random()*(max-min+1)+min);
		int point2 = point(point1,length);
		if(point1 > point2)
		{
			int temp = point1;
			point1 = point2;
			point2 = temp;
		}
		for(int i = point1; i <= point2; ++i)
		{
			child1.setIndex(i, parent1.getIndex(i));
			child2.setIndex(i, parent2.getIndex(i));
		}
		for(int i = 0; i < length; ++i)
		{
			if(child1.getIndex(i) == 0)
			{
				for(int j = 0; j < length; ++j)
				{
					if(!find(parent2.getIndex(j),child1))
					{
						child1.setIndex(i, parent2.getIndex(j));
						break;
					}
				}
			}
			
		}
		for(int i = 0; i < length; ++i)
		{
			if(child2.getIndex(i) == 0)
			{
				for(int j = 0; j < length; ++j)
				{
					if(!find(parent1.getIndex(j),child2))
					{
						child2.setIndex(i, parent1.getIndex(j));
						break;
					}
				}
			}
			
		}
	    Chromosome[] result = new Chromosome[2];
	    result[0] = child1;
	    result[1] = child2;
		return result;
	}
	
	
	// Does mutation in the given chromosome
	private static Chromosome mutation(Chromosome q, double prob)
	{ 
		int max = 1;
		int min = 0;
		int size = q.size() - 1 ;
		double random = Math.floor(Math.random()*(max-min+1)+min);
		int cRandom1 = 0;
		int cRandom2 = 0;
		if(prob <=  random)
		{
			cRandom1 = (int) Math.floor(Math.random()*(size-min+1)+min);
			cRandom2 = (int) Math.floor(Math.random()*(size-min+1)+min);
			if(cRandom1 != cRandom2)
			{
				int temp = q.getIndex(cRandom1);
				q.setIndex(cRandom1, q.getIndex(cRandom2));
				q.setIndex(cRandom2, temp);
			}
		}
			return q;
	}
	
	
	//Helper. Returns a random number different from the given value
	private static int point(int difValue, int size)
	{
		int min = 1;
		int max = size - 1;
		int point = (int)Math.floor(Math.random()*(max -min+1)+min);
		if(point != difValue)
			return point;
		return point(difValue,size);
	}
	
	
	//Helper. Checks if there is a queen in the given position 
	private static boolean find(int value, Chromosome child)
	{
		for(int i = 0; i < child.size();++i)
		{
			if(child.getIndex(i) == value)
				return true;
		}
		return false;
	}
	

	// Where the code starts its execution
	public static void main(String[] args) {
		int n = 0;
		System.out.println("Please input N: ");
		Scanner sc= new Scanner(System.in); 
		n = sc.nextInt();
		Chromosome[] totalPopulation = new Chromosome[pSize];
		if(n == 2 || n == 3)
		{
			System.out.print("There is no solution for N = " + n);
		}
		else
		{
			for(int i = 0; i < pSize; ++i)
			{
				totalPopulation[i] = generateChromosome(n);
			}            
			
			Chromosome solution = solveNQueens(totalPopulation,0,5500);
			if(solution != null)
			{
				System.out.println("The number of iterations is: " + iterations);
				solution.print();
			}
		}
		sc.close();
	}

}
