package classes;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import gui.fileChooser;

public class LinkedList 
{
	private Node HEAD;
	private int length;
	private String filePath;
	
	public LinkedList()
	{
		HEAD = null;
		length = 0;
	}
	
	@SuppressWarnings("unused")
	public boolean isFull()
	{
	    Node new_node = new Node();
	    if(new_node != null)
	    {
	        return false;
	    }
	    return true;
	}
	
	public int lengthOfList()
	{
		return length;
	}
	
	public boolean isEmpty()
	{
		if (HEAD == null)
		{
			return true;
		}
		return false;
	}
	
	public String getFileName(){
		return filePath;
	}
	
	public void setFileName(String fileName){
		this.filePath = fileName;
	}
	
	public String loadDataset()
	{
		long StartTime =0;
		long EndTime =0;
		try
		{
			fileChooser file = new fileChooser();
			FileInputStream fstream = new FileInputStream(file.openFile("Linked List and Binary Search Tree"));
			StartTime = System.nanoTime();
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			while ((strLine = br.readLine()) != null)
			{
				String words[] = strLine.split("\\t");
				this.Dictionary(words[0], words[1], words[2]);
			}
			in.close();
		}
		catch(Exception e)
		{
			System.err.println("Error: " + e.getMessage());
		}
		EndTime = System.nanoTime();
		return "Loading dataset from file to linked list took: "+(EndTime - StartTime)+" nanoseconds";
	}
	
	public void Dictionary(String word, String partOfSpeech, String meaning)
	{
		if(isFull())
		{
			System.out.println("Error - Cannot insert the item");
		}
		else
		{
			word = word.replaceAll("^\\W", "");
			Node newWordMeaning = new Node(word, partOfSpeech, meaning );
			Node temp = new Node();
			if(isEmpty())
			{
				this.HEAD = newWordMeaning;
			}
			else
			{
				temp = this.HEAD;
			    while ((temp.getNextWordMeaning() != null)) 
			    {
			           temp = temp.getNextWordMeaning();
			    }
			    temp.setNextWordMeaning(newWordMeaning);
			}
			length++;
		}
	}
	
	public String bubbleSort() 
	{
		long StartTime = 0;
		long EndTime = 0;
		if (isEmpty() || this.HEAD.getNextWordMeaning() == null)
		{
	        System.out.println("An empty list is already sorted."); 
		}
		else 
		{
			StartTime = System.nanoTime();
	        Node current = this.HEAD;
	        boolean swapDone = true;
	        while (swapDone) 
	        {
	        	swapDone = false;
	            while (current != null) 
	            {
	            	if(current.getNextWordMeaning() != null && current.getWord().compareToIgnoreCase(current.getNextWordMeaning().getWord()) > 0)
	            	{
	                    String word = current.getWord();
	                    String partOfSpeech = current.getPartOfSpeech();
	                    String meaning = current.getMeaning();
	                    current.setWord(current.getNextWordMeaning().getWord());
	                    current.setPartOfSpeech(current.getNextWordMeaning().getPartOfSpeech());
	                    current.setMeaning(current.getNextWordMeaning().getMeaning());
	                    current.getNextWordMeaning().setWord(word);
	                    current.getNextWordMeaning().setPartOfSpeech(partOfSpeech);
	                    current.getNextWordMeaning().setMeaning(meaning);
	                    swapDone = true;
	                }
	                current = current.getNextWordMeaning();
	            }
	            current = this.HEAD;
	        }
	    	EndTime = System.nanoTime();
	    }
		return "Sorting the linked list took: "+(EndTime - StartTime)+" nanoseconds";
	}
	
	public String addToDictionary(String word, String partOfSpeech, String meaning)
	{
		long StartTime = 0;
		long EndTime = 0;
		
	    word = Character.toUpperCase(word.charAt(0)) + word.substring(1);
		
		if(isFull())
		{
			System.out.println("Error - Cannot insert the item");
		}
		else
		{
			StartTime = System.nanoTime();
			Node newWordMeaning = new Node(word, partOfSpeech, meaning);
			Node currPtr = new Node();
			Node temp = new Node();
			boolean insert = false;
			if(isEmpty())
			{
				this.HEAD = newWordMeaning;
			}
			if(this.locate(newWordMeaning.getWord()) != -1){
				return "Sorry! the word '"+newWordMeaning.getWord()+"' already exists";
			}
			else
			{
				if(this.HEAD.getWord().compareToIgnoreCase(word)>0)
				{
					newWordMeaning.setNextWordMeaning(this.HEAD);
					this.HEAD = newWordMeaning;
				}
				else
				{
					temp = this.HEAD;
					currPtr = this.HEAD.getNextWordMeaning();
					while(currPtr != null){
						if(temp.getWord().compareToIgnoreCase(word)<0 && currPtr.getWord().compareToIgnoreCase(word)>0)
						{
							temp.setNextWordMeaning(newWordMeaning);
							newWordMeaning.setNextWordMeaning(currPtr);
							insert = true;
							break;
						}
						else
						{
							temp = currPtr;
							currPtr = currPtr.getNextWordMeaning();
						}
					}
				}
				if(insert==false)
				{
					temp.setNextWordMeaning(newWordMeaning);
				}
				length++;
				EndTime = System.nanoTime();
			}
		}
		return "Adding the word '"+word+"' to the linked list took: "+(EndTime - StartTime)+" nanoseconds";
	}
	
	public String lookUp(String searchValue)
	{
		long StartTime = 0;
		long EndTime = 0;
		Node currentPtr = this.HEAD;
		boolean found = false;
		StartTime = System.nanoTime();
		int index = this.locate(searchValue)-1;
		
		while(currentPtr != null)
		{
			if(currentPtr.getWord().compareToIgnoreCase(searchValue)==0)
			{
				found = true;
				index++;
				System.out.println();
				
				System.out.println("Found at index: " +Integer.toString(index)+"\n"+currentPtr.getWord()+"\t"+currentPtr.getPartOfSpeech()+"\t"+currentPtr.getMeaning());
				
			}
			currentPtr = currentPtr.getNextWordMeaning();
		}
		EndTime = System.nanoTime();
		if(!found)
		{
			return "Not Found";
		}
		return "Searching for the word '"+searchValue+"' in the linked list took: "+(EndTime - StartTime)+" nanoseconds";
	}
	
	public int locate(String element)
	{
		int index = 0;
		if(isEmpty())
		{
			System.out.println("The list is empty");
		}
		else
		{
			Node currentPtr = this.HEAD;
			while(currentPtr != null)
			{
				if(currentPtr.getWord().compareToIgnoreCase(element)==0)
				{
					return index;
				}
				index++;
				currentPtr = currentPtr.getNextWordMeaning();
			}
		}
		return -1;
	}
	
	public void validateSentence(String sentence)
	{
		long StartTime = 0;
		long EndTime = 0;
		ArrayList<String> arr = new ArrayList<String>();
		String word[] = sentence.split("[\\W]");
		Scanner in = new Scanner(System.in);
		int i;
		int found;
		StartTime = System.nanoTime();
		for(i=0;i<word.length;i++)
		{
			found = this.locate(word[i]);
			if(found==-1)
			{
				arr.add(word[i]);
			}
		}
		EndTime = System.nanoTime();
		System.out.println("Validaing the sentence took (Linked List): "+(EndTime - StartTime)+" nanoseconds");
		int j;
		if(arr.size()!=0)
		{
			for(j=0;j<arr.size();j++)
			{
				System.out.println("'"+arr.get(j)+"' was not found in the dictionary, would you like to add it to the database of words?\n y/n");
				String respond = in.nextLine();
				if(respond.equals("y"))
				{
					System.out.println("Enter part of speech: ");
					String partOfSpeech = in.nextLine();
					System.out.println("Enter Meaning: ");
					String meaning = in.nextLine();
					this.addToDictionary(arr.get(j), partOfSpeech, meaning);
				}
				else
				{
					System.out.println("'"+arr.get(j)+"' was ignored..");
				}
			}
		}
	}
	
	public String displayDictionary()
	{
		long StartTime = 0;
		long EndTime = 0;
		Node currNode = HEAD;
		
		StartTime = System.nanoTime();
		try{
			while(currNode != null)
			{
				currNode.display();
				currNode = currNode.getNextWordMeaning();
			}
		}catch(Exception e){
			System.out.println(e);
		}
		EndTime = System.nanoTime();
		return "Displaying words in the linked list took: "+(EndTime - StartTime)+" nanoseconds";
	}
}
