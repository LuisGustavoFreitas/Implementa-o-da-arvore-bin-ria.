class Node {
    int info;
    Node esquerda, direita;

    Node(int info) {
        this.info = info;
    }
}

public class ArvoreBinaria {
    Node raiz;

    // Inserir
    void inserir(int valor) {
        raiz = inserirRec(raiz, valor);
    }

    Node inserirRec(Node atual, int valor) {
        if (atual == null) return new Node(valor);
        if (valor < atual.info) atual.esquerda = inserirRec(atual.esquerda, valor);
        else if (valor > atual.info) atual.direita = inserirRec(atual.direita, valor);
        return atual;
    }

    // In-ordem
    void inOrdem(Node n) {
        if (n != null) {
            inOrdem(n.esquerda);
            System.out.print(n.info + " ");
            inOrdem(n.direita);
        }
    }

    // Pré-ordem
    void preOrdem(Node n) {
        if (n != null) {
            System.out.print(n.info + " ");
            preOrdem(n.esquerda);
            preOrdem(n.direita);
        }
    }

    // Pós-ordem
    void posOrdem(Node n) {
        if (n != null) {
            posOrdem(n.esquerda);
            posOrdem(n.direita);
            System.out.print(n.info + " ");
        }
    }

    // Remover menor
    Node removerMenor(Node n) {
        if (n == null) return null;
        if (n.esquerda == null) return n.direita;
        n.esquerda = removerMenor(n.esquerda);
        return n;
    }

    // Remover maior
    Node removerMaior(Node n) {
        if (n == null) return null;
        if (n.direita == null) return n.esquerda;
        n.direita = removerMaior(n.direita);
        return n;
    }

    // Remover valor
    Node remover(Node n, int valor) {
        if (n == null) return null;
        if (valor < n.info) n.esquerda = remover(n.esquerda, valor);
        else if (valor > n.info) n.direita = remover(n.direita, valor);
        else {
            if (n.esquerda == null) return n.direita;
            else if (n.direita == null) return n.esquerda;
            Node sucessor = menor(n.direita);
            n.info = sucessor.info;
            n.direita = remover(n.direita, sucessor.info);
        }
        return n;
    }

    Node menor(Node n) {
        while (n.esquerda != null) n = n.esquerda;
        return n;
    }

    // Teste
    public static void main(String[] args) {
        ArvoreBinaria arv = new ArvoreBinaria();
        int[] valores = {14, 15, 4, 9, 7, 18, 3, 5, 16, 20, 17};
        for (int v : valores) arv.inserir(v);

        System.out.print("In-ordem: ");
        arv.inOrdem(arv.raiz);
        System.out.println();

        System.out.print("Pré-ordem: ");
        arv.preOrdem(arv.raiz);
        System.out.println();

        System.out.print("Pós-ordem: ");
        arv.posOrdem(arv.raiz);
        System.out.println();

        arv.raiz = arv.removerMenor(arv.raiz);
        System.out.print("Após remover menor: ");
        arv.inOrdem(arv.raiz);
        System.out.println();

        arv.raiz = arv.removerMaior(arv.raiz);
        System.out.print("Após remover maior: ");
        arv.inOrdem(arv.raiz);
        System.out.println();

        arv.raiz = arv.remover(arv.raiz, 15);
        System.out.print("Após remover 15: ");
        arv.inOrdem(arv.raiz);
        System.out.println();
    }
}
