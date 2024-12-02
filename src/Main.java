import java.util.ArrayList;
import java.util.NoSuchElementException;

class HeapTree {
    private ArrayList<Integer> heap;

    public HeapTree() {
        heap = new ArrayList<>();
    }

    // Метод для добавления элемента в кучу
    public void add(int value) {
        heap.add(value);
        siftUp(heap.size() - 1);
    }

    // Метод для удаления элемента по значению
    public void remove(int value) {
        int index = heap.indexOf(value);
        if (index == -1) {
            throw new NoSuchElementException("Element not found in the heap");
        }
        removeAt(index);
    }

    // Метод для удаления элемента по индексу
    private void removeAt(int index) {
        int lastIndex = heap.size() - 1;
        if (index == lastIndex) {
            heap.remove(lastIndex);
        } else {
            int lastValue = heap.remove(lastIndex);
            heap.set(index, lastValue);
            siftDown(index);
            siftUp(index);
        }
    }

    // Метод для поиска элемента в куче
    public boolean contains(int value) {
        return heap.contains(value);
    }

    // Вспомогательный метод для "подъема" элемента
    private void siftUp(int index) {
        int value = heap.get(index);
        while (index > 0) {
            int parentIndex = (index - 1) / 2;
            int parentValue = heap.get(parentIndex);
            if (value >= parentValue) {
                break;
            }
            heap.set(index, parentValue);
            index = parentIndex;
        }
        heap.set(index, value);
    }

    // Вспомогательный метод для "опускания" элемента
    private void siftDown(int index) {
        int value = heap.get(index);
        int lastIndex = heap.size() - 1;
        while (index < lastIndex) {
            int leftChildIndex = 2 * index + 1;
            int rightChildIndex = 2 * index + 2;
            int smallestChildIndex = leftChildIndex;

            if (leftChildIndex > lastIndex) {
                break; // Нет детей
            }
            if (rightChildIndex <= lastIndex && heap.get(rightChildIndex) < heap.get(leftChildIndex)) {
                smallestChildIndex = rightChildIndex;
            }
            if (value <= heap.get(smallestChildIndex)) {
                break;
            }
            heap.set(index, heap.get(smallestChildIndex));
            index = smallestChildIndex;
        }
        heap.set(index, value);
    }

    // Метод для получения размера кучи
    public int size() {
        return heap.size();
    }

    // Метод для проверки, пуста ли куча
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    // Пример использования
    public static void main(String[] args) {
        HeapTree heapTree = new HeapTree();

        // Добавление элементов
        heapTree.add(10);
        heapTree.add(5);
        heapTree.add(20);
        heapTree.add(3);
        heapTree.add(8);

        // Проверка наличия элементов
        System.out.println("Contains 5: " + heapTree.contains(5)); // true
        System.out.println("Contains 15: " + heapTree.contains(15)); // false

        // Удаление элемента
        System.out.println("Removing 5");
        heapTree.remove(5);
        System.out.println("Contains 5: " + heapTree.contains(5)); // false

        // Проверка размера кучи
        System.out.println("Current size of heap: " + heapTree.size()); // 4

        // Удаление еще одного элемента
        System.out.println("Removing 10");
        heapTree.remove(10);
        System.out.println("Current size of heap after removing 10: " + heapTree.size()); // 3

    }
}