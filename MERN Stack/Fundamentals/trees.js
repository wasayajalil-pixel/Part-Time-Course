import Queue from "./queue.js";
class Node {
  constructor(val) {
    this.val = val;
    this.left = null;
    this.right = null;
  }
}

class BTS {
  constructor(root) {
    this.root = null;
  }

  addNewNode(root, val) {
    if (!root) {
      return new Node(val);
    }
    if (val < root.val) {
      root.left = this.addNewNode(root.left, val);

    } else {
      root.right = this.addNewNode(root.right, val);
    }
    return root;
  }

  searchBST (root,val){
    if (root == null){
      return null;
    }
    if()
  }



  inOrder(root) {
    if (root !== null) {
      this.inOrder(root.left);
      console.log(root.val);
      this.inOrder(root.right);
    }
  }

  preOrder(root) {
    if (root !== null) {
      console.log(root.val);
      this.preOrder(root.left);
      this.preOrder(root.right);
    }
  }

  postOrder(root) {
    if (root !== null) {
      this.postOrder(root.left);
      this.postOrder(root.right);
      console.log(root.val);
    }
  }

  //Breadth First Search
  BFS(root) {
    if (root == null) {
      return null;
    }
    const queue = new Queue();
    queue.enqueue(root);

    while (!queue.isEmpty()) {
      currentNode = queue.dequeue();
      if (currentNode.left != null) {
        queue.enqueue(currentNode.left);
      }

      if (currentNode.right != null) {
        queue.enqueue(currentNode.right);
      }
    }
  }
}

