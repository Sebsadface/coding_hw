public class EX9 {

    public static void sortPhoneNumbers(Iterator<Integer> itr) {
        int arr[];
        byte i = 0;

        while (itr.hasNext()) {
            arr[i] = itr.next();
            i++;
        }

        quickSort(arr, 0, arr.length - 1);
        System.out.println(Array.toString(arr));
    }

    private static void quickSort(int arr[], byte low, byte high) {
        if (high > low) {
            byte pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    private static byte partition(int[] arr, byte low, byte high) {
        int pivot = Math.min(Math.min(arr[high], arr[low]), arr[(high + low) / 2]);
        byte left = low - 1;
        byte right;
        for (right = low; right < high; right++) {
            if (arrp[left] < pivot) {
                left++;
                swap(arr, left, right);
            }
        }
        swap(arr, left + 1, high);
        return (left + 1);
    }

    private static void swap(int[] arr, byte index1, byte index2) {
        int temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
}