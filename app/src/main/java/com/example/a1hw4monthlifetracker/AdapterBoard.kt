package layout

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.a1hw4monthlifetracker.ItemClicker
import com.example.a1hw4monthlifetracker.databinding.ItemBoardBinding

class AdapterBoard(private val list: ArrayList<BoardModel>,
                   private val listener: ItemClicker) :
    RecyclerView.Adapter<AdapterBoard.BoardViewHolder>() {
    inner class BoardViewHolder(private val binding: ItemBoardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun Onbind(boardModel: BoardModel) {
            binding.imgeBoard.load(boardModel.image)
            binding.buttonNext.text = boardModel.button
            binding.description.text = boardModel.descriptin
            binding.buttonNext.setOnClickListener{
                if(adapterPosition==list.size-1){
                listener.itemClick()}
                else if (adapterPosition==list.size-3){
                    listener.click1Page()
                }
                else if (adapterPosition==list.size-2){
                    listener.click2Page()
                }



            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoardViewHolder {
        return BoardViewHolder(
            ItemBoardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: BoardViewHolder, position: Int) {
        holder.Onbind(list[position])
    }

    override fun getItemCount(): Int {
        return list.size
    }

}