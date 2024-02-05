import java.util.*;

public class Arkenstone {
    public static int maxCheck = 0;
    public static LinkedList<Integer> pathCheck = new LinkedList<>();
    public static void main(String args[]) {
        LinkedList<Integer> path; 
        int[][] map = makeMap();
        int[] answers = new int[8];
        for(int i = 0; i < 8; i++){
            path = new LinkedList<>(); 
            path.add(map[path.size()][i]);
            answers[i] = findPath(map, path, i);
        }
        findAnswer(map, answers);
        printPath(Arkenstone.pathCheck);
        findVault(map, Arkenstone.pathCheck.getLast());
    }

    public static int[][] makeMap(){
        int [][] map = {{35, 89, 52, 66, 82, 20, 95, 21},
                        {79,  5, 14, 23, 78, 37, 40, 74},
                        {32, 59, 17, 25, 31,  4, 16, 63},
                        {91, 11, 77, 48, 13, 71, 92, 15},
                        {56, 70, 47, 64, 22, 88, 67, 12},
                        {83, 97, 94, 27, 65, 51, 30,  7},
                        {10, 41,  1, 86, 46, 24, 53, 93},
                        {96, 33, 44, 98, 75, 68, 99, 84}};
        return map;
    }

    public static int findPath(int[][] map, LinkedList<Integer> path, int loc){
        if(path.size() >= 8){
            if(getTotal(path) > Arkenstone.maxCheck){
                Arkenstone.maxCheck = getTotal(path);
                    Arkenstone.pathCheck = path;

            }
            return getTotal(path);
        }
        else{
            LinkedList<Integer> path2 = new LinkedList<>(path);
            LinkedList<Integer> path3 = new LinkedList<>(path);
            if(loc <= 0){
                path3.add(map[path.size()][(loc+1)]);
                path.add(map[path.size()][loc]);
                return findMax(findPath(map, path3, (loc+1)),findPath(map, path, loc), 0);
            }
            else if(loc >= 7){
                path2.add(map[path.size()][(loc-1)]);
                path.add(map[path.size()][loc]);
                return findMax(findPath(map, path2, (loc-1)), findPath(map, path, loc), 0);
            }
            else{
                
                path3.add(map[path.size()][(loc+1)]);
                path2.add(map[path.size()][(loc-1)]);
                path.add(map[path.size()][loc]);
                return findMax(findPath(map, path2, (loc-1)), findPath(map, path, loc), findPath(map, path3, (loc+1)));
            }
        }
    }

    public static int getTotal(LinkedList<Integer> path){
        int pTotal = 0;
        for(int i = 0; i < path.size(); i++){
            pTotal += path.get(i); 
        }
        return pTotal;
    }

    public static int findMax(int f, int s, int t){
        if(f > s){
            if(f > t){
                return f;
            }
            else{
                return t;
            }
        }
        else{
            if(s > t){
                return s;
            }
            else{
                return t;
            }
        }
    }

    public static void findAnswer(int[][] map, int[] a){
        int max = 0;
        int maxIndex = 0;

        for(int i = 0; i < 8; i++){
            if(a[i] > max){
                max = a[i];
                maxIndex = i;
            }
        }
        System.out.println("The best path starts at " + map[0][maxIndex] + " and totals " + max);
    }

    public static void printPath(LinkedList<Integer> path){
        System.out.print("Path: ");
        for(int i = 0; i < path.size(); i++){
            System.out.print(path.get(i) + "  ");
        }
        System.out.println();
    }

    public static void findVault(int[][] map, int last){
        for(int i = 0; i < 8; i++){
            if(map[7][i] == last){
                System.out.println("The gem is behind vault number " + (i+1));
            }
        }
    }
}
