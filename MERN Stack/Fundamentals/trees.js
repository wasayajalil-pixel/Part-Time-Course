class Node{
    constructor(val){
        this.val = val;
        this.left = null;
        this.right = null;
    }
}

class BTS{
    constructor(root){
        this.root = root;
    }

    addNewNode(root,val){
        if(!root){
            return new Node(val);
        }
        if(val < root.val){
            root.left = this.addNewNode(root.left,val)
        }
        else {
            root.right = this.addNewNode(root.right,val)
        }
        return root

    }
}

