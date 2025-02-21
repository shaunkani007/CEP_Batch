public class basic {

    // CREATE BALANCED BST

    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBST(int[] nums, int si, int ei) {
        if (si > ei) {
            return null;
        }
        int mid = (si + ei) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, si, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, ei);
        return root;
    }

    // INSRT

    public TreeNode insertIntoBST(TreeNode root, int val) {
        if (root == null) {
            return new TreeNode(val);
        }
        if (root.val < val) {
            root.right = insertIntoBST(root.right, val);
        } else {
            root.left = insertIntoBST(root.left, val);
        }
        return root;
    }

    // DELETE FROM BST

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) {
            return null;
        }
        if (root.val == key) {
            if (root.left == null || root.right == null) {
                return root.left == null ? root.right : root.left;
            }
            int mval = max(root.left);
            root.val = mval;
            root.left = deleteNode(root.left, mval);
        } else if (root.val < key) {
            root.right = deleteNode(root.right, key);
        } else {
            root.left = deleteNode(root.left, key);
        }
        return root;
    }

    public int max(TreeNode root) {
        if (root.right == null) {
            return root;
        }
        return max(root.right);
    }

    // FIND

    public TreeNode searchBST(TreeNode root, int val) {
        TreeNode c = root;
        while (c != null) {
            if (c.val == val) {
                return c;
            } else if (c.val < val) {
                c = c.right;
            } else {
                c = c.left;
            }
        }
        return null;
    }

    // inorder pred and succ
    TreeNode pred = null;
    TreeNode succ = null;

    public TreeNode inorderPredecessorSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return root;
        }
        if (root.val == p.val) {
            if (root.left != null) {
                TreeNode c = root.left;
                while (c.right != null) {
                    c = c.right;
                }
                pred = c;
            }
            if (root.right != null) {
                TreeNode c = root.right;
                while (c.left != null) {
                    c = c.left;
                }
                succ = c;
            }

        } else if (root.val < p.val) {
            pred = root;
            inorderPredecessorSuccessor(root.right, p);
        } else {
            succ = root;
            inorderPredecessorSuccessor(root.left, p);
        }
        return root;
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) {
            return null;
        }
        if (root.val == p.val) {
            if (root.right != null) {
                TreeNode c = root.right;
                while (c.left != null) {
                    c = c.left;
                }
                return c;
            }
        } else if (root.val < p.val) {
            return inorderSuccessor(root.right, p);
        } else {
            TreeNode rval = inorderSuccessor(root.left, p);
            return rval == null ? root : rval;
        }
        return null;
    }

    // RANGE SUM BST
    public int rangeSumBST(TreeNode root, int l, int r) {
        if (root == null) {
            return 0;
        }
        if (root.val <= r && root.val >= l) {
            return rangeSumBST(root.left, l, r) + rangeSumBST(root.right, l, r) + root.val;
        }
        if (l > root.val) {
            return rangeSumBST(root.right, l, r);
        } else {
            return rangeSumBST(root.left, l, r);
        }
    }

    // GREATER SUM BST

    public TreeNode bstToGst(TreeNode root) {
        int[] ans = new int[1];
        bstToGst(root, ans);
        return ans;
    }

    public void bstToGST(TreeNode root, int[] ans) {
        if (root == null) {
            return;
        }
        bstToGST(root.right, ans);
        ans[0] += root.val;
        root.val = ans[0];
        bstToGST(root.left, ans);
    }

}