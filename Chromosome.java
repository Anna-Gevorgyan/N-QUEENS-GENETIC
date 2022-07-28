
public class Chromosome {
	int[] chromosome;
	
	// Default constructor 
	public Chromosome()
	{
		chromosome = new int[0];
	}
	
	// Constructor taking size as an argument
	public Chromosome(int size)
	{
		chromosome = new int[size];
	}
	
	//returns the placement of queen in idex^th column 
	public int getIndex(int index)
	{
		validateIndex(index);
		return chromosome[index];
	}
	
	// sets the position of queen in index^th column
	public void setIndex(int index, int value)
	{
		validateIndex(index);
		chromosome[index] = value;
	}
	
	// returns the length of the chromosome
	public int size()
	{
		return chromosome.length;
	}
	
	//prints the chromosome 
	public void print()
	{
		for(int i = 0; i < chromosome.length; ++i)
		{
			System.out.print(chromosome[i]);
			if(i != chromosome.length - 1)
			{
				System.out.print( ", ");
			}
		}
		System.out.println();
	}
	
	// throws an error if the index is out of bounds
	private void validateIndex(int index)
	{
		if(index < 0 || index >= chromosome.length)
			throw new IndexOutOfBoundsException("Error: Index out of bounds!"); 
	}
}
