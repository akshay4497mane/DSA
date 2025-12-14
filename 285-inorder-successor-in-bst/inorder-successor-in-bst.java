/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
/*
Intuition first. Forget code. Visualize the walk.

Inorder = left → node → right
Successor = “the next node you would visit”.

Now two mutually exclusive situations.

---

Situation 1: p has a right child
After visiting p, inorder says: go to p.right, then as left as possible.
So successor is the **leftmost node of p.right**.

This has NOTHING to do with root walking or candidates.

---

Situation 2: p has NO right child
Then successor must be **above p**.

Which ancestor?
The first ancestor that:
• p lies in its LEFT subtree
• and hasn’t been visited yet

How do we find that without parent pointer?

By walking from root to p.

---

Now connect this to the code.

```
TreeNode ans = null;
```

ans = “best candidate ancestor so far”.

While walking from root:

```
if (p.val < root.val)
```

You go LEFT.

Meaning:
p is in root’s left subtree.
In inorder, root is visited **after** its left subtree.
So root might be the successor.

Save it:

```
ans = root;
```

```
else if (p.val > root.val)
```

You go RIGHT.

Meaning:
root and its left subtree are already visited.
root can never be successor.

So ignore it.

---

Why loop stops at p
Once you reach p, you have collected all ancestors where:
• you turned left
• hence they could be next

The **last such ancestor** is the closest successor.

---

Final decision logic:

If p.right exists
→ ignore ans
→ successor is leftmost of right subtree

Else
→ successor is ans

---

Mental one-liner (remember this):

• Right subtree beats ancestors
• No right subtree → last left-turn ancestor wins

That’s exactly what your code implements.

If you can say this clearly in an interview, you’re already at senior-level clarity.

*/
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode ans = null;
        while( root != null && root.val != p.val ){
            if( p.val < root.val ){
                ans = root;
                root = root.left;
            }else if( p.val > root.val ){
                root = root.right;
            }
        }

        if (root == null) return null;
        if (root.right != null) {
            TreeNode cur = root.right;
            while (cur.left != null) cur = cur.left;
            return cur;
        }

        return ans;
    }
}