package rafdatabase.model.dal.datastructures;

/**
 * Created by j2arr on 8/23/2016.
 */
public class TreeNode<E> {

    private E data;
    private TreeNode<E> parent;
    private TreeNode<E> left;
    private TreeNode<E> right;

    public TreeNode(E data, TreeNode<E> parent) {
        this.data = data;
        this.parent = parent;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public TreeNode<E> getParent() {
        return parent;
    }

    public void setParent(TreeNode<E> parent) {
        this.parent = parent;
    }

    public TreeNode<E> getLeftChild() {
        return getLeft();
    }

    public TreeNode<E> addLeftChild(E data) {
        this.setLeft(new TreeNode<>(data, this));
        return getLeft();
    }

    public TreeNode<E> getRightChild() {
        return getRight();
    }

    public TreeNode<E> addRightChild(E data) {
        this.setRight(new TreeNode<>(data, this));
        return getRight();
    }

    @Override
    public String toString() {
        return data.toString();
    }

    public TreeNode<E> getLeft() {
        return left;
    }

    public void setLeft(TreeNode<E> left) {
        this.left = left;
    }

    public TreeNode<E> getRight() {
        return right;
    }

    public void setRight(TreeNode<E> right) {
        this.right = right;
    }
}
