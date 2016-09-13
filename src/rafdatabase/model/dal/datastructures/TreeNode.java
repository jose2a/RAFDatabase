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
        return left;
    }

    public TreeNode<E> addLeftChild(E data) {
        this.left = new TreeNode<>(data, this);
        return left;
    }

    public TreeNode<E> getRightChild() {
        return right;
    }

    public TreeNode<E> addRightChild(E data) {
        this.right = new TreeNode<>(data, this);
        return right;
    }

    @Override
    public String toString() {
        return data.toString();
    }
}
