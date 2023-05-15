package main

import (
	"bufio"
	"fmt"
	"math/rand"
	"os"
	"strconv"
	"time"
)

var EMPTY_CELL = " "
var PLAYER = "X"
var COMPUTER = "O"

var board [3][3]string
var emptyCellCount = 9
var reader *bufio.Reader = bufio.NewReader(os.Stdin)

func main() {
	rand.Seed(time.Now().UnixNano())
	initBoard()
	winner := EMPTY_CELL
	for winner == EMPTY_CELL && hasEmptyCell() {
		printBoard()
		processPlayerMove()
		winner = checkWinner()
		if winner != EMPTY_CELL || !hasEmptyCell() {
			break
		}
		processComputerMove()
		winner = checkWinner()
	}
	printBoard()
	if winner == EMPTY_CELL {
		fmt.Println("Tie!")
	} else if winner == PLAYER {
		fmt.Printf("You win!\n")
	} else {
		fmt.Printf("Computer wins!\n")
	}
}

func initBoard() {
	for i := 0; i < 3; i++ {
		for j := 0; j < 3; j++ {
			board[i][j] = EMPTY_CELL
		}
	}
}

func hasEmptyCell() bool {
	return emptyCellCount > 0
}

func processPlayerMove() {
	var x, y int
	for {
		x = getPlayerInput("Enter row: ")
		y = getPlayerInput("Enter col: ")
		if !isEmptyCell(x, y) {
			fmt.Println("Cell is not empty. Please enter another cell.")
			continue
		}
		break
	}
	board[x][y] = PLAYER
	emptyCellCount--
}

func isEmptyCell(x, y int) bool {
	return board[x][y] == EMPTY_CELL
}

func getPlayerInput(prompt string) int {
	var num int
	for {
		fmt.Print(prompt)
		input, err := reader.ReadString('\n')
		if err != nil {
			fmt.Println("Invalid input. Please enter an integer.")
			continue
		}

		input = input[:len(input)-1] // Remove the newline character
		num, err = strconv.Atoi(input)
		if err != nil || num < 0 || num > 2 {
			fmt.Println("Invalid input. Please enter a number between 0 and 2.")
			continue
		}
		break
	}
	return num
}

func processComputerMove() {
	var x, y int
	for {
		x = rand.Intn(3)
		y = rand.Intn(3)
		if !isEmptyCell(x, y) {
			continue
		}
		break
	}
	board[x][y] = COMPUTER
	emptyCellCount--
}

func checkWinner() string {
	for i := 0; i < 3; i++ {
		if board[i][0] == board[i][1] && board[i][1] == board[i][2] {
			return board[i][0]
		}
	}

	for i := 0; i < 3; i++ {
		if board[0][i] == board[1][i] && board[1][i] == board[2][i] {
			return board[0][i]
		}
	}

	if board[0][0] == board[1][1] && board[1][1] == board[2][2] {
		return board[0][0]
	}
	if board[0][2] == board[1][1] && board[1][1] == board[2][0] {
		return board[0][2]
	}
	return EMPTY_CELL
}

func printBoard() {
	fmt.Printf("\n    0   1   2\n")
	fmt.Printf("0 | %s | %s | %s |\n", board[0][0], board[0][1], board[0][2])
	fmt.Printf("  |---|---|---|\n")
	fmt.Printf("1 | %s | %s | %s |\n", board[1][0], board[1][1], board[1][2])
	fmt.Printf("  |---|---|---|\n")
	fmt.Printf("2 | %s | %s | %s |\n\n", board[2][0], board[2][1], board[2][2])
}
