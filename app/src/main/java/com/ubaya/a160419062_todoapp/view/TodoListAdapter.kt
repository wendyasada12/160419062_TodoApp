package com.ubaya.a160419062_todoapp.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.a160419062_todoapp.R
import com.ubaya.a160419062_todoapp.databinding.TodoItemListBinding
import com.ubaya.a160419062_todoapp.model.Todo
import kotlinx.android.synthetic.main.todo_item_list.view.*

class TodoListAdapter (
    val todoList:ArrayList<Todo>,
    val adapterOnClick:(Todo) -> Unit): RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>(),TodoCheckedChangeListener,TodoEditClick {
    class TodoViewHolder(var view: TodoItemListBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<TodoItemListBinding>(inflater, R.layout.todo_item_list, parent, false)

        return TodoViewHolder(view)
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.view.todo = todoList[position]
        holder.view.listener = this
        holder.view.editListener = this
        /*
        holder.view.checkTask.setText(todoList[position].title)
        holder.view.imgEdit.setOnClickListener{
            val action = TodoListFragmentDirections.actionToEditTodoFragment(todoList[position].uuid)
            Navigation.findNavController(it).navigate(action)
        }
        holder.view.checkTask.setOnCheckedChangeListener { compoundButton, isChecked ->
            if(isChecked == true) {
                adapterOnClick(todoList[position])
            }
        }
        holder.view.checkTask.setOnCheckedChangeListener { compoundButton, b ->
            adapterOnClick(todoList[position])
        }
        */
    }
    override fun getItemCount(): Int {
        return todoList.size
    }

    fun updateTodoList(newTodoList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newTodoList)
        notifyDataSetChanged()
    }

    override fun onCheckChanged(cb: CompoundButton, isChecked: Boolean, obj: Todo) {
        if(isChecked){
            adapterOnClick(obj)
        }
    }

    override fun onTodoEditClick(v: View) {
        val uuid = v.tag.toString().toInt()
        val action = TodoListFragmentDirections.actionToEditTodoFragment(uuid)

        Navigation.findNavController(v).navigate(action)
    }
}
