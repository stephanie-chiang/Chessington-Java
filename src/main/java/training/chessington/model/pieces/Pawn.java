package training.chessington.model.pieces;

import training.chessington.model.Board;
import training.chessington.model.Coordinates;
import training.chessington.model.Move;
import training.chessington.model.PlayerColour;

import java.util.ArrayList;
import java.util.List;

public class Pawn extends AbstractPiece {
    public Pawn(PlayerColour colour) {
        super(Piece.PieceType.PAWN, colour);
    }

    public List<Move> addAllowedMoves(Move anAllowedMove, List<Move> allowedMoves) {
        allowedMoves.add(anAllowedMove);
        return allowedMoves;
    }

    public List<Move> addToAllowedMovesIfCapturing(
            Board board, Coordinates from, Coordinates coordsPieceToTake, List<Move> allowedMoves) {
        
        if (board.isSquareOccupied(coordsPieceToTake) 
            && board.get(coordsPieceToTake).getColour() != colour)
            allowedMoves.add(new Move(from, coordsPieceToTake));
        
        return allowedMoves;
    }

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> allowedMoves = new ArrayList<Move>();

        int direction = (colour == PlayerColour.WHITE) ? -1 : 1;

        int startRow = (colour == PlayerColour.WHITE) ? 6 : 1;

        int endRow = (colour == PlayerColour.WHITE) ? 0 : 7;

        int startCol = 0;

        int endCol = 0;

        Coordinates diagonallyRight = from.plus(direction * 1, 1);

        Coordinates diagonallyLeft = from.plus(direction * 1, -1);

        if (from.getRow() == startRow 
            && !board.isSquareOccupied(from.plus(direction * 2, 0))) {
            addAllowedMoves(new Move(from, from.plus(direction * 2, 0)), allowedMoves);
        }

        if (colour == PlayerColour.WHITE) {

            if (from.getRow() > endRow) {
                //can move forward one square if unoccupied
                if (!board.isSquareOccupied(from.plus(direction * 1, 0))) 
                    addAllowedMoves(new Move(from, from.plus(direction * 1, 0)), allowedMoves);
                
                //moving diagonally if sq contains enemy piece
                if (from.getCol() != startCol) 
                    addToAllowedMovesIfCapturing(board, from, diagonallyLeft, allowedMoves);
                    
                if (from.getCol() != endCol)
                addToAllowedMovesIfCapturing(board, from, diagonallyRight, allowedMoves);
            }
        }
            

        if (colour == PlayerColour.BLACK) {
            if (from.getRow() < endRow) {
                //can move forward one square if unoccupied
                if (!board.isSquareOccupied(from.plus(direction * 1, 0)))
                    addAllowedMoves(new Move(from, from.plus(direction * 1, 0)), allowedMoves);

                //moving diagonally if sq contains enemy piece
                if (from.getCol() != startCol) 
                    addToAllowedMovesIfCapturing(board, from, diagonallyLeft, allowedMoves);
                    
                if (from.getCol() != endCol)
                    addToAllowedMovesIfCapturing(board, from, diagonallyRight, allowedMoves);
            }
        }

        return allowedMoves;
    }
}
