package kz.edu.nu.cs.se.hw;

public class test {
	
	public static PlayableRummy create(String... players) {

        // Edit this line to test with implementations with different names
        return new Rummy(players);
	}
    
    public static void main(String args[]) {
//    	KeywordInContext kwic;
//        kwic = new MyKeywordInContext("frankensteinsample", "frankensteinsample.txt");
//        System.out.println("check1");
//        kwic.txt2html();
//        System.out.println("check2");

    	PlayableRummy rummy = create("Alice", "Bob", "Dan");
    	rummy.initialDeal();
    	System.out.println(rummy.getNumCardsInDeck());
    	String[] arr0 = rummy.getHandOfPlayer(0);
    	String[] arr1 = rummy.getHandOfPlayer(1);
    	String[] arr2 = rummy.getHandOfPlayer(2);
    	for (int i = 0; i < arr2.length; i++) {
    		System.out.println(arr0[i] + ',' + arr1[i] + ',' + arr2[i]);
    	}
    }
}
    