

	/* 
    
    class Node 
    	int data;
    	Node left;
    	Node right;
	*/
	public static void topView(Node root) {
        Queue<Pair> queue = new ArrayDeque<>();
        Map<Integer, Integer> viewMap = new TreeMap<>();
        queue.add(new Pair(root, 0));
        while(!queue.isEmpty()){
            Pair curr = queue.poll();
            if (!viewMap.containsKey(curr.horDist)){
                viewMap.put(curr.horDist, curr.node.data);
            }            
            if(curr.node.left!=null){
                queue.add(new Pair(curr.node.left, curr.horDist-1));
            }            
            if(curr.node.right!=null){
                queue.add(new Pair(curr.node.right, curr.horDist+1));
            }            
        }        
        System.out.print(viewMap.entrySet().stream().map(Map.Entry::getValue).map(String::valueOf).collect(java.util.stream.Collectors.joining(" ")));        
    }
    
    static class Pair{
        Node node;
        int horDist;
        
        Pair(Node node, int horDist){
            this.node = node;
            this.horDist = horDist;
        }        
    }

