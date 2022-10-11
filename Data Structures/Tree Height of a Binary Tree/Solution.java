

	/*
    class Node 
    	int data;
    	Node left;
    	Node right;
	*/
	public static int height(Node root) {
      	// Write your code here.
        if (root==null){
            return 0;
        }
        
        int leftHeight = 0;
        int rightHeight = 0;
        
        if(root.left!=null){
            leftHeight = height(root.left)+1;
        }
        
        if(root.right!=null){
            rightHeight = height(root.right)+1;
        }
                
        return leftHeight>rightHeight?leftHeight:rightHeight;      
    }

