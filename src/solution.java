import java.util.*;

public class solution {
    public static void main(String[] args){
        int[] a={1,2,1};
       System.out.println(new solution().pivotIndex(a));

    }

//    int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
//1631. 最小体力消耗路径
//    你准备参加一场远足活动。给你一个二维rows x columns的地图heights，其中heights[row][col]表示格子(row, col)的高度。一开始你在最左上角的格子(0, 0)，且你希望去最右下角的格子(rows-1, columns-1)（注意下标从 0 开始编号）。你每次可以往 上，下，左，右四个方向之一移动，你想要找到耗费 体力 最小的一条路径。
//
//    一条路径耗费的 体力值是路径上相邻格子之间 高度差绝对值的 最大值决定的。
//
//    请你返回从左上角走到右下角的最小体力消耗值。

    public int minimumEffortPath(int[][] heights) {//迪杰斯特拉算法
        int m = heights.length;
        int n = heights[0].length;
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] edge1, int[] edge2) {
                return edge1[2] - edge2[2];
            }
        });
        pq.offer(new int[]{0, 0, 0});

        int[] dist = new int[m * n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        boolean[] seen = new boolean[m * n];

        while (!pq.isEmpty()) {
            int[] edge = pq.poll();
            int x = edge[0], y = edge[1], d = edge[2];
            int id = x * n + y;
            if (seen[id]) {
                continue;
            }
            if (x == m - 1 && y == n - 1) {
                break;
            }
            seen[id] = true;
            for (int i = 0; i < 4; ++i) {
                int nx = x + dirs[i][0];
                int ny = y + dirs[i][1];
                if (nx >= 0 && nx < m && ny >= 0 && ny < n && Math.max(d, Math.abs(heights[x][y] - heights[nx][ny])) < dist[nx * n + ny]) {
                    dist[nx * n + ny] = Math.max(d, Math.abs(heights[x][y] - heights[nx][ny]));
                    pq.offer(new int[]{nx, ny, dist[nx * n + ny]});
                }
            }
        }

        return dist[m * n - 1];
    }


    public int pivotIndex(int[] nums) {
        if(nums.length-1==0)
            return 0;
        if(nums.length-1<2)
            return -1;
        int la=0,ra=0;
        for(int i=1;i<=nums.length-1;i++){
            ra+=nums[i];
        }
        for(int center=0;center<=nums.length-1;center++){
            if (la == ra)
                return center;
            else if(la!=ra && center!=nums.length-1){
                la+=nums[center];
                ra-=nums[center+1];
            }
        }
        return -1;
    }


//    628
//    给你一个整型数组 nums ，在数组中找出由三个数组成的最大乘积，并输出这个乘积。
    //能一遍循环结束就用循环一遍，排序消耗资源

    public int maximumProduct(int[] nums) {
//        Arrays.sort(nums);//排序
//        int n=nums.length;
//        if((nums[n-1]*nums[n-2]*nums[n-3]) >(nums[0]*nums[1]*nums[n-1]))
//            return  nums[n-1]*nums[n-2]*nums[n-3];
//        else
//            return  nums[0]*nums[1]*nums[n-1];

        if (nums.length < 3) {
            return 0;
        }
        int min1 = Integer.MAX_VALUE, min2 = Integer.MAX_VALUE;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;
        for (int n : nums) {
            if (n < min1) {
                min2 = min1;
                min1 = n;
            } else if (n < min2) {
                min2 = n;
            }
            if (n > max1) {
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (n > max2) {
                max3 = max2;
                max2 = n;
            } else if (n > max3) {
                max3 = n;
            }
        }
        return Math.max(min1 * min2 * max1, max1 * max2 * max3);
    }


//    141. 环形链表
//    给定一个链表，判断链表中是否有环。
//
//    如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
//
//    如果链表中存在环，则返回 true 。 否则，返回 false 。
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/linked-list-cycle
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode slow = head, fast = head;
        //如果存在环，则快指针不会找到null,反之则会不断循环直到遇到
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return false;
            }
            //当相遇返回true
            if (fast == slow) {
                return true;

            }
        }
        return false;
    }


//   142 环路链接2
//    给定一个链表，返回链表开始入环的第一个节点。如果链表无环，则返回null。
//为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
//说明：不允许修改给定的链表。
//进阶：
//你是否可以使用 O(1) 空间解决此题？
    //简单方法用哈希表解决问题，进阶用快慢指针解决问题，如果存在环，快慢指针必定相遇
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        //如果存在环，则快指针不会找到null,反之则会不断循环直到遇到
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            //当遇到时，fast跑的路程为slow的两倍 f:2nb s:nb n为f比s多跑的圈数，而b为环的点数
            //随后ptr从起点出发，当而ptr和s相遇时的路程为 ptr:a s:a+nb 因为nb为环，所以当ptr与s相遇时为环的起点
            if (fast == slow) {
                ListNode ptr = head;
                while (ptr != slow) {
                    ptr = ptr.next;
                    slow = slow.next;
                }
                return ptr;
            }
        }
        return null;
    }
    //989. 数组形式的整数加法
//    对于非负整数X而言，X的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果x = 1231，那么其数组形式为[1,2,3,1]。
//
//    给定非负整数 X 的数组形式A，返回整数X+K的数组形式。
    public List<Integer> addToArrayForm(int[] A, int K) {
        //按照A的长度进行计算，随后再对K进行判断，将K分位加入a
//        ArrayList a=new ArrayList();
//        for(int i=A.length-1;i>=0;i--){
//            a.add((A[i]+K)%10);
//            K=(A[i]+K)/10;
//        }
//        if(K!=0) {
//            while (K != 0)
//            {
//                a.add(K%10);
//                K=K/10;
//            }
//        }
//        Collections.reverse(a);
//        return a;
        //通过K的长度进行判断，微微提高内存使用率
        ArrayList a=new ArrayList();
        int len=A.length-1;
        while(K!=0 || len>=0){
            if(len<0){
                a.add(K%10);
                K=K/10;
            }else {
                a.add((A[len]+K)%10);
                K=(A[len]+K)/10;
                len--;
            }
        }
        Collections.reverse(a);
        return a;
    }

//    1319. 连通网络的操作次数
//    用以太网线缆将n台计算机连接成一个网络，计算机的编号从0到n-1。线缆用connections表示，其中connections[i] = [a, b]连接了计算机a和b。
//
//    网络中的任何一台计算机都可以通过网络直接或者间接访问同一个网络中其他任意一台计算机。
//
//    给你这个计算机网络的初始布线connections，你可以拔开任意两台直连计算机之间的线缆，并用它连接一对未直连的计算机。请你计算并返回使所有计算机都连通所需的最少操作次数。如果不可能，则返回-1 。
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/number-of-operations-to-make-network-connected
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    //判断有几棵树，如果只有一颗，则全部联通，如果有两颗，则需要修改一次
    List<Integer>[] edges;
    boolean[] used;
    public int makeConnected(int n, int[][] connections) {
            if (connections.length < (n - 1)) {
                return -1;
            }

            edges = new List[n];
            for (int i = 0; i < n; ++i) {
                edges[i] = new ArrayList<Integer>();
            }
            for (int[] conn : connections) {
                edges[conn[0]].add(conn[1]);
                edges[conn[1]].add(conn[0]);
            }

            used = new boolean[n];
            int ans = 0;
            for (int i = 0; i < n; ++i) {
                if (!used[i]) {
                    dfs(i);
                    ++ans;
                }
            }

            return ans - 1;
        }

        public void dfs(int u) {
            used[u] = true;
            for (int v : edges[u]) {
                if (!used[v]) {
                    dfs(v);
                }
            }
        }

//    674. 最长连续递增序列
//    给定一个未经排序的整数数组，找到最长且 连续递增的子序列，并返回该序列的长度。
//
//    连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
//
//
//
//    来源：力扣（LeetCode）
//    链接：https://leetcode-cn.com/problems/longest-continuous-increasing-subsequence
//    著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
    public int findLengthOfLCIS(int[] nums) {
        //空数组
        if (nums.length==0)
            return 0;
        //非空必有一个
        int len=1;
        int max=1;
        //一次循环遍历
        for(int i=1;i<nums.length;i++){
            if(nums[i]>nums[i-1]){
                len++;
            }
            else {
                if(len>max){
                    max=len;
                }
                len=1;
            }

        }//结束后再确认
        if(len>max){
            max=len;
        }
        return max;
    }
//    1128. 等价多米诺骨牌对的数量
//    给你一个由一些多米诺骨牌组成的列表dominoes。
//    如果其中某一张多米诺骨牌可以通过旋转 0度或 180 度得到另一张多米诺骨牌，我们就认为这两张牌是等价的。
//    形式上，dominoes[i] = [a, b]和dominoes[j] = [c, d]等价的前提是a==c且b==d，或是a==d 且b==c。
//    在0 <= i < j < dominoes.length的前提下，找出满足dominoes[i] 和dominoes[j]等价的骨牌对 (i, j) 的数量。

    public int numEquivDominoPairs(int[][] dominoes) {
        int[] num = new int[100];
        int ret = 0;
        for (int[] domino : dominoes) {
            int val = domino[0] < domino[1] ? domino[0] * 10 + domino[1] : domino[1] * 10 + domino[0];
            ret += num[val];
            num[val]++;
        }
        return ret;
    }


}


