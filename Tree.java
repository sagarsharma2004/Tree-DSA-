import java.util.Scanner;

class TreeNode {
    int data;
    TreeNode LeftNode;
    TreeNode MidNode;
    TreeNode RightNode;

    public TreeNode(int data) {
        this.data = data;
        this.LeftNode = null;
        this.MidNode = null;
        this.RightNode = null;
    }
}

public class Tree {
    public TreeNode rootNode;

    public Tree() {
        this.rootNode = null;
    }

    public Tree(int data, Tree LeftTree, Tree MidTree, Tree RightTree) {
        this.rootNode = new TreeNode(data);
        if (LeftTree != null) {
            this.rootNode.LeftNode = LeftTree.rootNode;
        } else {
            this.rootNode.LeftNode = null;
        }

        if (MidTree != null) {
            this.rootNode.MidNode = MidTree.rootNode;
        } else {
            this.rootNode.MidNode = null;
        }

        if (RightTree != null) {
            this.rootNode.RightNode = RightTree.rootNode;
        } else {
            this.rootNode.RightNode = null;
        }
    }

    public void display() {
        display(rootNode);
    }

    private void display(TreeNode temp) {
        if (temp == null) {
            return;
        }
        String result = "" + temp.data;
        if (temp.LeftNode != null) {
            System.out.print(temp.LeftNode.data + " <== " + result + " ");
        } else {
            System.out.print("X <== " + result + " ");
        }

        if (temp.MidNode != null) {
            System.out.print(" __ " + temp.MidNode.data + " __ ");
        } else {
            System.out.print(" __ X __ ");
        }

        if (temp.RightNode != null) {
            System.out.print(" " + result + " ==> " + temp.RightNode.data);
        } else {
            System.out.print(" " + result + " ==> X");
        }
        System.out.println();

        // Recursively display child nodes
        display(temp.LeftNode);
        display(temp.MidNode);
        display(temp.RightNode);
    }

    public void insertNode() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter root data: ");
        int data = sc.nextInt();
        rootNode = insertNode(rootNode, data, sc);
    }

    private TreeNode insertNode(TreeNode node, int data, Scanner sc) {
        if (node == null) {
            node = new TreeNode(data);
        }

        System.out.print("Enter data for left child of " + data + " (or -1 for no child): ");
        int leftData = sc.nextInt();
        if (leftData != -1) {
            node.LeftNode = insertNode(null, leftData, sc);
        }

        System.out.print("Enter data for mid child of " + data + " (or -1 for no child): ");
        int midData = sc.nextInt();
        if (midData != -1) {
            node.MidNode = insertNode(null, midData, sc);
        }

        System.out.print("Enter data for right child of " + data + " (or -1 for no child): ");
        int rightData = sc.nextInt();
        if (rightData != -1) {
            node.RightNode = insertNode(null, rightData, sc);
        }

        return node;
    }

    private int max(TreeNode temp) {
        if (temp == null) {
            return Integer.MIN_VALUE;
        }
        int left = max(temp.LeftNode);
        int mid = max(temp.MidNode);
        int right = max(temp.RightNode);
        int greater = Math.max(mid, right);
        int greatest = Math.max(greater, left);
        return Math.max(temp.data, greatest);
    }

    public int Max() {
        return max(rootNode);
    }

    public boolean Find(int val){
        return find(rootNode,val);
    }

    private boolean find(TreeNode temp, int val) {
        if(temp==null){
            return false;
        }
        if(temp.data==val){
            return true;
        }
        boolean left=find(temp.LeftNode, val);
        boolean mid=find(temp.MidNode, val);
        boolean right=find(temp.RightNode, val);
        return left||mid||right;
    }

    public int  Height(){
        return height(rootNode);
    }

    private int height(TreeNode temp) {
        if(temp==null){
            return 0;
        }
        int left=height(temp.LeftNode);
        int mid=height(temp.MidNode);
        int right=height(temp.RightNode);
        return Math.max(left, Math.max(mid, right))+1;
    }

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.insertNode();
        tree.display();
        int higher = tree.Max();
        System.out.println(higher);
        System.out.println(tree.Find(56));
        int len=tree.Height();
        System.out.println(len);
    }
}
