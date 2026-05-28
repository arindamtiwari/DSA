class TrieNode:
    def __init__(self):
        self.children = {}
        self.idx = -1


class Solution:
    def stringIndices(self, wordsContainer, wordsQuery):
        root = TrieNode()

        # returns True if word at i is better than word at j
        def better(i, j):
            if j == -1:
                return True

            if len(wordsContainer[i]) < len(wordsContainer[j]):
                return True

            if len(wordsContainer[i]) == len(wordsContainer[j]):
                return i < j

            return False

        # Build Trie
        for i, word in enumerate(wordsContainer):
            rev = word[::-1]

            node = root

            if better(i, node.idx):
                node.idx = i

            for ch in rev:
                if ch not in node.children:
                    node.children[ch] = TrieNode()

                node = node.children[ch]

                if better(i, node.idx):
                    node.idx = i

        ans = []

        # Process queries
        for word in wordsQuery:
            rev = word[::-1]

            node = root

            for ch in rev:
                if ch not in node.children:
                    break

                node = node.children[ch]

            ans.append(node.idx)

        return ans