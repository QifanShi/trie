package Trie;

/**
 * This class implements a trie data structure. 
 * The class contains three methods: insert a word, delete a word and search a word.
 * Instead of recursion, the implementation uses iteration.
 * @author Qifan Shi
 * @version 1.2 6/29
 * 
 *
 */
public class Trie {

	class TrieNode {
		
		TrieNode[] children; 
		boolean isWord;
		
		TrieNode(){
			children = new TrieNode[26]; // only lower-case letters
			isWord = false;
		}
		
	}
	
	TrieNode root;
	
	Trie(){} // lazy mode
	
	/**
	 * This method performs a inserting function.
	 * @param word the word to be inserted in the trie.
	 */
	public void insert(String word){
		
		// a null check
		if(word == null) return;
		
		// iteratively insert the word letter by letter
		if(root == null) root = new TrieNode();
		
		TrieNode t = root;
		while(!word.equals("")){
			
			int index = word.charAt(0) - 'a';
			if(t.children[index] == null){
				t.children[index] = new TrieNode();
			}
			
			t = t.children[index];
			word = word.substring(1);
		}
		
		t.isWord = true;
	}
	
	/**
	 * This method performs a searching function.
	 * @param word the word to be searched in the trie.
	 * @return true if word is found in the trie, otherwise false.
	 */
	public boolean search(String word){
		
		if(word == null || root == null) return false;
		
		TrieNode t = root;
		while(!word.equals("")){
			
			int index = word.charAt(0) - 'a';
			if(t.children[index] == null)
				return false;
			
			t = t.children[index];
			word = word.substring(1);
		}
		
		return t.isWord; 
	}
	
	/**
	 * The method performs a deleting function. 
	 * @param word the word to be deleted from the trie.
	 */
	public void delete(String word){
		
		// corner cases
		if(word == null || root == null) return;
//		if(word.length() == 0) {
//			root.isWord = false;
//			return;
//		}
		
		// node d marks the "highest node" where we can delete the rest of the path after d
		TrieNode d = root;
		int ind = !word.equals("") ? word.charAt(0) - 'a': -1;// index marks the "cut position" in d
		
		TrieNode t = root;
		while(!word.equals("")){
			
			int index = word.charAt(0) - 'a';
			
			// word to be deleted might not be contained in the trie 
			if(t.children[index] == null) return;
			
			// check if any sibling exists in the current level of  the trie 
			for(int i = 0; i < 26; i++){
				if(i != index && t.children[i] != null){
					d = t;
					ind = index;
				}
			}
			
			// check if a sub-path is also a word
			if(t.isWord == true){
				d = t;
				ind = index;
			}
			
			// go to the next level in the trie
			t = t.children[index];
			word = word.substring(1);
		}
		
		if(ind == -1) {
			root.isWord = false;
			return;
		}
		
		// check if word is a substring of another one
		for(int i = 0; i < 26; i++){
			if(t.children[i] != null){
				t.isWord = false;
				return;
			}
		}

		d.children[ind] = null; // delete
	}
	
	public void clear(){
		root = null;
	}
	
	/**
	 * The main method contains some test cases for the trie
	 * @param args
	 */
	public static void main(String[] args){
		
		Trie trie = new Trie();
		
//		trie.insert(null);
//		System.out.println(trie.search(null));
//		trie.delete(null);
//		trie.delete("");
//		
//		System.out.println(trie.search(""));
//		trie.insert("");
//		System.out.println(trie.search(""));
//		trie.delete("");
//		System.out.println(trie.search(""));
		
		
		// build a trie
		System.out.println("Building a trie...");
		System.out.println("insert word: a");
		trie.insert("a");
		System.out.println("delete word: a");
		trie.delete("a");
		System.out.println();
		
		//trie.clear();
		
		System.out.println("Insert word: ant");
		trie.insert("ant");
		System.out.println("Insert word: anti");
		trie.insert("anti");
		System.out.println("Insert word: antio");
		trie.insert("antio");
		System.out.println("Insert word: in");
		trie.insert("in");
		System.out.println("Insert word: is");
		trie.insert("is");
		System.out.println("Insert word: and");
		trie.insert("and");
		System.out.println("Insert word: andy");
		trie.insert("andy");
		System.out.println("Insert word: an");
		trie.insert("an");
		trie.insert("an");
		
		System.out.println();
		System.out.println("Search word ant: " + trie.search("ant"));
		System.out.println("Search word anti: " + trie.search("anti"));
		System.out.println("Search word antio: " + trie.search("antio"));
		
		System.out.println();
		System.out.println("Delete word ant from the trie.");
		trie.delete("ant");
		System.out.println("Search word ant: " + trie.search("ant"));
		System.out.println("Search word anti: " + trie.search("anti"));
		System.out.println("Search word antio: " + trie.search("antio"));
		
		System.out.println();
		System.out.println("Insert word ant back to the trie.\n Delete word anti from the trie.");
		trie.insert("ant");
		trie.delete("anti");
		System.out.println("Search word ant: " + trie.search("ant"));
		System.out.println("Search word anti: " + trie.search("anti"));
		System.out.println("Search word antio: " + trie.search("antio"));
		
		
		System.out.println();
		System.out.println("Insert word anti back to the trie.\n Delete word antio from the trie.");
		trie.insert("anti");
		trie.delete("antio");
		System.out.println("Search word ant: " + trie.search("ant"));
		System.out.println("Search word anti: " + trie.search("anti"));
		System.out.println("Search word antio: " + trie.search("antio"));
		
		System.out.println();
		System.out.println("Insert word antio back to the trie.\n Delete word in from the trie.");
		trie.insert("antio");
		trie.delete("in");
		System.out.println("Search word in: " + trie.search("in"));
		System.out.println("Search word is: " + trie.search("is"));
		
		System.out.println();
		System.out.println("Insert word in back to the trie.\n Delete word an from the trie.");
		trie.insert("in");
		trie.delete("an");
		System.out.println("Search word a: " + trie.search("a"));
		System.out.println("Search word an: " + trie.search("an"));
		System.out.println("Search word and: " + trie.search("and"));
		System.out.println("Search word andy: " + trie.search("andy"));
		System.out.println("Search word ant: " + trie.search("ant"));
		System.out.println("Search word anti: " + trie.search("anti"));
		System.out.println("Search word antio: " + trie.search("antio"));

	}
	
	
}
