package rafdatabase.model.dal.datastructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by Jose Arriaga on 8/23/2016.
 * Binary Search Tree. This class can be replace by using the one provided by Java.
 */
public class BinarySearchTree<E extends Comparable<? super E>> {

    private TreeNode<E> root; // Root node

    public BinarySearchTree() {
        root = null;
    }

    /**
     * Find an element in the tree. In this case we can find an element even if the object is not complete.
     * The class used by the BST will implement the Comparable interface, that is where, we can specify how the
     * element will be found.
     * @param toFind Element to find.
     * @return The element.
     */
    public E find(E toFind) {
        TreeNode<E> current = root;

        int comp;

        while (current != null) {
            comp = toFind.compareTo(current.getData());

            if (comp < 0) {
                current = current.getLeftChild();
            }
            else if (comp > 0) {
                current = current.getRightChild();
            }
            else {
                return current.getData();
            }
        }
        return null;
    }

    public boolean insert(E toInsert) {
        if (root == null) {
            root = new TreeNode<>(toInsert, null);
            return true;
        }

        TreeNode<E> current = root;

        int comp = toInsert.compareTo(current.getData());

        while ((comp < 0 && current.getLeftChild() != null) || (comp > 0 && current.getRightChild() != null)) {

            if (comp < 0) {
                current = current.getLeftChild();
            }
            else if (comp > 0) {
                current = current.getRightChild();
            }

            comp = toInsert.compareTo(current.getData());
        }

        if (comp < 0) {
            current.addLeftChild(toInsert);
        }
        else if (comp > 0) {
            current.addRightChild(toInsert);
        }
        else  {
            return false;
        }
        return true;
    }

    public boolean delete(E toDelete) {
        if (root == null) {
            return false;
        }

        // Find node to delete
        TreeNode<E> current = findNode(toDelete, root);

        int dir = 0;

        if (current.getParent().getLeftChild() == current) {
            dir = -1;
        } else if (current.getParent().getRightChild() == current) {
            dir = 1;
        }

        // Case 1. Node is a leaf node
        if (current.getLeftChild() == null && current.getRightChild() == null) {
            current.setParent(null);
            /*if (dir < 0) {
                current.getParent().addLeftChild(null);
            } else {
                current.getParent().addRightChild(null);
            }*/
            return true;
        }

        // Case 2. Node has one child
        if ((current.getLeftChild() != null && current.getRightChild() == null)
                || (current.getLeftChild() == null && current.getRightChild() != null)) {

            if (current.getLeftChild() != null) {
                if (dir < 0) {
                    current.getParent().addLeftChild(current.getLeftChild().getData());
                } else {
                    current.getParent().addRightChild(current.getLeftChild().getData());
                }
            } else {
                if (dir < 0) {
                    current.getParent().addLeftChild(current.getRightChild().getData());
                } else {
                    current.getParent().addRightChild(current.getRightChild().getData());
                }
            }
        }

        if (current.getLeftChild() != null && current.getRightChild() != null) {
            TreeNode<E> node = findSmallestValue(current.getRightChild());
            delete(node.getData());
            current.setData(node.getData());
        }
        return true;
    }

    private TreeNode<E> findSmallestValue(TreeNode<E> current) {

        while (current.getLeftChild() != null || current.getRightChild() != null) {

            if (current.getLeftChild() != null) {
                current = current.getLeftChild();
            } else if (current.getRightChild() != null) {
                current = current.getRightChild();
            }
        }
        return current;
    }

    protected TreeNode<E> findNode(E toFind, TreeNode<E> current) {
        if (current == null) {
            return null;
        }

        int comp = toFind.compareTo(current.getData());

        if (comp < 0)
            return findNode(toFind, current.getLeftChild());
        else if (comp > 0)
            return findNode(toFind, current.getRightChild());
        else
            return current;
    }

    public List<E> getNodes() {
        List<E> nodes = new ArrayList<>();
        Queue<TreeNode<E>> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()) {
            TreeNode<E> current = q.remove();
            if (current != null) {
                nodes.add(current.getData());
                q.add(current.getLeftChild());
                q.add(current.getRightChild());
            }
        }

        return nodes;
    }

    public void insertNodes(List<E> nodes) {

        if (nodes == null)
            return;

        for (E node : nodes) {
            this.insert(node);
        }
    }

}
