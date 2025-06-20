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


    @Override
    public List<Move> getAllowedMoves(Coordinates from, Board board) {
        List<Move> allowedMoves = new ArrayList<Move>();

        //get player colour at coordinates?
        //playercolour of current object method is being called on

        if (colour == PlayerColour.WHITE) {
            Move newMove = new Move(from, from.plus(-1, 0));
            allowedMoves.add(newMove);
        } 

        if (colour == PlayerColour.BLACK) {
            Move newMove = new Move(from, from.plus(1,0));
            allowedMoves.add(newMove);
        }

        System.out.println(from);

        return allowedMoves;
    }
}
