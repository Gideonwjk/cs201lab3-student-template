import java.util.*;

public class Q1Test {
     public static void main(String[] args) {

          String result, traversal1, traversal2, traversal3;

          traversal1 = "1-2";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "3-2-1";
          traversal3 = "2-3-1";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "3-1-2-5-4";
          traversal2 = "2-1-4-5-3";
          traversal3 = "1-2-3-4-5";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "10-20-30-40-50";
          traversal2 = "20-10-30-40-50";
          traversal3 = "10-50-40-30-20";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();
     } 

      // write your codes here
     public static String verify(String traversal1, String traversal2, String traversal3){
          if(traversal1.length() != traversal2.length() && traversal1.length() != traversal3.length()){
               return "Invalid traversals";
          }

          String postOrder = null;
          String inOrder = null;
          String[] orders = new String[3];
          String[] traversals = {traversal1, traversal2, traversal3};
          boolean found = false;
          for(int i = 0; i < traversals.length; i++){
               orders[i] = "Preorder";
               Node head = binaryTreeBuilder(traversals[i]);
               String inOrderTemp = getinOrder(head);
               String postOrderTemp = getpostOrder(head);
               inOrderTemp = inOrderTemp.substring(0, inOrderTemp.length() - 1);
               postOrderTemp = postOrderTemp.substring(0, postOrderTemp.length() - 1);
               // System.out.println("PreOrder == " + traversals[i]);
               // System.out.println("Inorder == " + inOrderTemp);
               // System.out.println("PostOrder == " + postOrderTemp);
               // System.out.println();
               // System.out.println();
               if(i == 0){
                    inOrder = traversals[1];
                    orders[1] = "Inorder";
                    postOrder = traversals[2];
                    orders[2] = "Postorder";
                    if(inOrderTemp.compareTo(inOrder) == 0 && postOrderTemp.compareTo(postOrder) == 0){
                         found = true;
                         break;
                    }else if(inOrderTemp.compareTo(postOrder) == 0 && postOrderTemp.compareTo(inOrder) == 0){
                         String temp = inOrder;
                         inOrder = postOrder;
                         postOrder = temp;
                         orders[1] = "Postorder";
                         orders[2] = "Inorder";
                         found = true;
                         break;
                    }
               }else if(i == 1){
                    inOrder = traversals[0];
                    postOrder = traversals[2];
                    orders[0] = "Inorder";
                    orders[2] = "Postorder";
                    if(inOrderTemp.compareTo(inOrder) == 0 && postOrderTemp.compareTo(postOrder) == 0){
                         found = true;
                         break;
                    }else if(inOrderTemp.compareTo(postOrder) == 0 && postOrderTemp.compareTo(inOrder) == 0){
                         String temp = inOrder;
                         inOrder = postOrder;
                         postOrder = temp;
                         orders[2] = "Inorder";
                         orders[0] = "Postorder";
                         found = true;
                         break;
                    }
               }else if(i == 2){
                    inOrder = traversals[0];
                    postOrder = traversals[1];
                    orders[0] = "Inorder";
                    orders[1] = "Postorder";
                    if(inOrderTemp.compareTo(inOrder) == 0 && postOrderTemp.compareTo(postOrder) == 0){
                         found = true;
                         break;
                    }else if(inOrderTemp.compareTo(postOrder) == 0 && postOrderTemp.compareTo(inOrder) == 0){
                         String temp = inOrder;
                         inOrder = postOrder;
                         postOrder = temp;
                         orders[1] = "Inorder";
                         orders[0] = "Postorder";
                         found = true;
                         break;
                    }
               }
          }
          if(found){
               return  "Traversal 1 - " + orders[0] + ", Traversal 2 - " + orders[1] + ", Traversal 3 - " + orders[2];
          }

          return "Invalid traversals";
          
     }

     public static Node binaryTreeBuilder(String traversal){
          String[] values = traversal.split("-");
          Node head = new Node(Integer.parseInt(values[0]));
          for(int i = 1; i < values.length; i++){
               Node temp = new Node(Integer.parseInt(values[i]));
               insert(head, temp);
          }
          return head;
     }
     public static void insert(Node head, Node inserting){
          Node prev = null;
          Node curr = head;
          while(curr != null){
               prev = curr;
               if(inserting.value > curr.value){
                    curr = curr.right;
               }else{
                    curr = curr.left;
               }
          }
          
          if(inserting.value > prev.value){
               prev.right = inserting;
          }else{
               prev.left = inserting;
          }
          return;
     }

     public static String getinOrder(Node node){
          if(node == null){
               return "";
          }
          return getinOrder(node.left) + node.value + "-" + getinOrder(node.right);
     }

     public static String getpostOrder(Node node){
          if(node == null){
               return "";
          }
          return getpostOrder(node.left) + getpostOrder(node.right) + node.value + "-";
     }

     public static class Node {
          public int value;
          public Node left;
          public Node right;

          public Node(int value){
               this.left = null;
               this.right = null;
               this.value = value;
          }
     }
}