package kotlinForJava.hw.board

fun createSquareBoard(width: Int): SquareBoard = object : SquareBoard {

    protected val limits = 1..width
    private val cells = arrayListOf<ArrayList<Cell>>()

    init {
        for (i in limits) {
            cells.add(arrayListOf())
            for (j in limits) {
                cells[i - 1].add(Cell(i, j))
            }
        }
    }

    override val width: Int
        get() = width

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        return if (i !in limits || j !in limits) {
            null
        } else {
            cells[i - 1][j - 1]
        }
    }

    override fun getCell(i: Int, j: Int): Cell {
        return if (i !in limits || j !in limits) {
            throw IllegalArgumentException("Incorrect $i $j")
        } else {
            cells[i - 1][j - 1]
        }
    }

    override fun getAllCells(): Collection<Cell> {
        val allCells = mutableListOf<Cell>()
        for (i in limits) {
            for (j in limits) {
                allCells.add(cells[i - 1][j - 1])
            }
        }
        return allCells
    }

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        val rowCells = mutableListOf<Cell>()
        for (j in jRange) {
            if (j in limits) {
                rowCells.add(cells[i - 1][j - 1])
            }
        }
        return rowCells
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        val columnCells = mutableListOf<Cell>()
        for (i in iRange) {
            if (i in limits) {
                columnCells.add(cells[i - 1][j - 1])
            }
        }
        return columnCells
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        return when (direction) {
            Direction.UP -> getCellOrNull(i - 1, j)
            Direction.DOWN -> getCellOrNull(i + 1, j)
            Direction.RIGHT -> getCellOrNull(i, j + 1)
            Direction.LEFT -> getCellOrNull(i, j - 1)
        }
    }
}

fun <T> createGameBoard(width: Int): GameBoard<T> = object : GameBoard<T> {

    private val cellValues = mutableMapOf<Cell, T?>()
    private val board = createSquareBoard(width)

    init {
        board.getAllCells().forEach { cell ->
            cellValues[cell] = null
        }
    }

    override val width: Int
        get() = width

    override fun getCellOrNull(i: Int, j: Int): Cell? =
        board.getCellOrNull(i, j)

    override fun getCell(i: Int, j: Int): Cell =
        board.getCell(i, j)

    override fun getAllCells(): Collection<Cell> =
        board.getAllCells()

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> =
        board.getRow(i, jRange)

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> =
        board.getColumn(iRange, j)

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        return with(board) {
            this@getNeighbour.getNeighbour(direction)
        }
    }

    override fun get(cell: Cell): T? {
        return cellValues[cell]
    }

    override fun set(cell: Cell, value: T?) {
        cellValues[cell] = value
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        return cellValues.filter { (_, value) -> predicate(value) }
            .map { (cell, _) -> cell }
            .toList()
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        return cellValues.filter { (_, value) -> predicate(value) }
            .map { (cell, _) -> cell }
            .toList()
            .firstOrNull()
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        return cellValues.any { (_, value) -> predicate(value) }
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        return cellValues.all { (_, value) -> predicate(value) }
    }
}

