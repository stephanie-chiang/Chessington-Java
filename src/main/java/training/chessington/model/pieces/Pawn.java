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

    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> allowedMoves = new ArrayList<Move>();

        if (colour == PlayerColour.WHITE) {
            if (from.getRow() == 6 && !board.isSquareOccupied(from.plus(-2, 0))) {
                addAllowedMoves(new Move(from, from.plus(-2, 0)), allowedMoves);
            }
            if (from.getRow() > 0  && !board.isSquareOccupied(from.plus(-1, 0)))
                addAllowedMoves(new Move(from, from.plus(-1, 0)), allowedMoves);
        } 

        if (colour == PlayerColour.BLACK) {
            if (from.getRow() == 1 && !board.isSquareOccupied(from.plus(2, 0))) {
                addAllowedMoves(new Move(from, from.plus(2, 0)), allowedMoves);
            }
            if (from.getRow() < 7 && !board.isSquareOccupied(from.plus(1, 0)))
                addAllowedMoves(new Move(from, from.plus(1, 0)), allowedMoves);
        }

        return allowedMoves;
    }
}
