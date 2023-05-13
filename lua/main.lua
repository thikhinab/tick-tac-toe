local EMPTY_CELL = " "
local PLAYER = "X"
local COMPUTER = "O"
local INVALID_VALUE = 0
local INVALID_CELL = 1
local VALID_CELL = 2

local free_cells = 9
local board = {
    { EMPTY_CELL, EMPTY_CELL, EMPTY_CELL },
    { EMPTY_CELL, EMPTY_CELL, EMPTY_CELL },
    { EMPTY_CELL, EMPTY_CELL, EMPTY_CELL }
}
-- Seed the random number generator
math.randomseed(os.time())

function main()
    local winner = EMPTY_CELL
    repeat
        print_board()
        process_player_move()
        winner = check_winner()
        if winner ~= EMPTY_CELL or has_no_empty_cell() then
            break
        end
        process_computer_move()
        winner = check_winner()
    until winner ~= EMPTY_CELL or has_no_empty_cell()
    print_board()
    if winner == PLAYER then
        print("You win!")
    elseif winner == COMPUTER then
        print("You lose!")
    else
        print("It's a tie!")
    end
end

function process_computer_move()
    if free_cells == 0 then
        return
    end

    local res = INVALID_CELL
    local row = -1
    local col = -1
    repeat
        row = math.random(1, 3)
        col = math.random(1, 3)
        res = is_valid_input(row, col)
    until res == VALID_CELL
    board[row][col] = COMPUTER
    free_cells = free_cells - 1
end

function check_winner()
    for i = 1, 3 do
        if board[i][1] == board[i][2] and board[i][1] == board[i][3] then
            return board[i][1]
        end
    end

    for i = 1, 3 do
        if board[1][i] == board[2][i] and board[1][i] == board[3][i] then
            return board[1][i]
        end
    end

    if board[1][1] == board[2][2] and board[1][1] == board[3][3] then
        return board[1][1]
    end
    if board[1][3] == board[2][2] and board[1][3] == board[3][1] then
        return board[1][3]
    end
    return EMPTY_CELL
end

function process_player_move()
    local res = INVALID_CELL
    local row = -1
    local col = -1
    repeat
        io.write("Enter row: ")
        row = tonumber(io.read())
        io.write("Enter col: ")
        col = tonumber(io.read())
        res = is_valid_input(row, col)
        if res == VALID_CELL then
            board[row][col] = PLAYER
            free_cells = free_cells - 1
        elseif res == INVALID_VALUE then
            print("Invalid value. Please enter a valid value.")
        elseif res == INVALID_CELL then
            print("Invalid cell. Please enter a valid cell.")
        end
    until res == VALID_CELL
end

function is_valid_input(row, col)
    local isValidRow = type(row) == "number" and math.floor(row) == row and row >= 1 and row <= 3
    local isValidCol = type(col) == "number" and math.floor(col) == col and col >= 1 and col <= 3
    if not isValidCol or not isValidRow then
        return INVALID_VALUE
    elseif board[row][col] ~= EMPTY_CELL then
        return INVALID_CELL
    else
        return VALID_CELL
    end
end

function has_no_empty_cell()
    return free_cells == 0
end

function print_board()
    io.write("\n| ", board[1][1], " | ", board[1][2], " | ", board[1][3], " |\n")
    io.write("|---|---|---|\n")
    io.write("| ", board[2][1], " | ", board[2][2], " | ", board[2][3], " |\n")
    io.write("|---|---|---|\n")
    io.write("| ", board[3][1], " | ", board[3][2], " | ", board[3][3], " |\n\n")
end

main()
