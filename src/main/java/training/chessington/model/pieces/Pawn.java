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

    private Boolean canCaptureDiagonally(
            Board board, Coordinates from, Coordinates coordsPieceToTake) {

            Piece pieceToTake = board.get(coordsPieceToTake);
            if (
                !board.isSquareOccupied(coordsPieceToTake) 
                || pieceToTake.getColour() == colour
                || pieceToTake.getType() == PieceType.KING
            ) {
                return false;
            }
        
        return true;
    }

    private boolean canAdvanceOneSquare(Coordinates from, Board board, int direction) {
        if (board.isSquareOccupied(from.plus(direction * 1, 0)))  {
            return false;
        }
        return true;
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
            allowedMoves.add(new Move(from, from.plus(direction * 2, 0)));
        }

        if ((direction == -1 && from.getRow() > endRow)
            || (direction == 1 && from.getRow() < endRow)) {
                //canAdvanceOneSquare
                if (canAdvanceOneSquare(from, board, direction)) {
                    allowedMoves.add(new Move(from, from.plus(direction * 1, 0)));
                }

                //moving diagonally if sq contains enemy piece
                if (from.getCol() != startCol
                    && canCaptureDiagonally(board, from, diagonallyLeft)) {
                    allowedMoves.add(new Move(from, diagonallyLeft));
                }
                    
                if (from.getCol() != endCol
                    && canCaptureDiagonally(board, from, diagonallyRight)) {
                    allowedMoves.add(new Move(from, diagonallyRight));
                }
            }
        return allowedMoves;
    }
}
