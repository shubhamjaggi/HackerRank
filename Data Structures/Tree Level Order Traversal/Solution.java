

	/* 
    
    class Node 
    	int data;
    	Node left;
    	Node right;
	*/
	public static void levelOrder(Node root) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        
        while(!queue.isEmpty()){
            Node curr = queue.poll();
            System.out.print(curr.data + " ");
            
            if(curr.left!=null){
                queue.add(curr.left);
            }
            
            if(curr.right!=null){
                queue.add(curr.right);
            }            
        }
    }

