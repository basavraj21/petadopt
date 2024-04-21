package com.project.petadopt;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.project.petadopt.databinding.ItemReceiveBinding;
import com.project.petadopt.databinding.ItemSentBinding;

import java.util.ArrayList;

public class MessagesAdapter extends RecyclerView.Adapter {

    Context context;
    ArrayList<Message> messages;

    final int ITEM_SENT = 1;
    final int ITEM_RECEIVE = 2;

    String senderRoom;
    String receiverRoom;


    public MessagesAdapter(Context context, ArrayList<Message> messages, String senderRoom, String receiverRoom) {
        this.context = context;
        this.messages = messages;
        this.senderRoom = senderRoom;
        this.receiverRoom = receiverRoom;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        if (viewType == ITEM_SENT) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_sent, parent, false);
            return new SentViewHolder(view);
        } else {

            View view = LayoutInflater.from(context).inflate(R.layout.item_receive, parent, false);
            return new ReceiverViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {


        Message message = messages.get(position);


        if (FirebaseAuth.getInstance().getUid().equals(message.getSenderId())) {
            return ITEM_SENT;
        } else {
            return ITEM_RECEIVE;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messages.get(position);

        FirebaseDatabase.getInstance().getReference()
                .child("chats")
                .child(senderRoom)
                .child("messages")
                .child(message.getMessageId()).setValue(message);

        FirebaseDatabase.getInstance().getReference()
                .child("chats")
                .child(receiverRoom)
                .child("messages")
                .child(message.getMessageId()).setValue(message);


        if (holder.getClass() == SentViewHolder.class) {
            SentViewHolder viewHolder = (SentViewHolder) holder;

            if (message.getMessage().contains("firebasestorage")) {
                viewHolder.binding.message.setText("1 File");
            } else {

                viewHolder.binding.message.setText(message.getMessage());
            }


//            viewHolder.binding.message.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    if (message.getMessage().contains("firebasestorage")) {
//
//                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(message.getMessage()));
//                        context.startActivity(browserIntent);
//                    } else {
//
//                        popup.onTouch(v, event);
//                    }
//                    return false;
//                }
//            });
//
//            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    View view = LayoutInflater.from(context).inflate(R.layout.delete_dialog, null);
//                    DeleteDialogBinding binding = DeleteDialogBinding.bind(view);
//                    AlertDialog dialog = new AlertDialog.Builder(context)
//                            .setTitle("Delete Message")
//                            .setView(binding.getRoot())
//                            .create();
//
////ștergerea mesajului obținând codul său, deoarece atât expeditorul, cât și cheia de primire sunt aceleași, așa că va fi șters pentru ambele
//
//                    binding.everyone.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            message.setMessage("Acest mesaj a fost sters.");
//                            message.setFeeling(-1);
//                            FirebaseDatabase.getInstance().getReference()
//                                    .child("chats")
//                                    .child(senderRoom)
//                                    .child("messages")
//                                    .child(message.getMessageId()).setValue(message);
//
//                            FirebaseDatabase.getInstance().getReference()
//                                    .child("chats")
//                                    .child(receiverRoom)
//                                    .child("messages")
//                                    .child(message.getMessageId()).setValue(message);
//                            dialog.dismiss();
//                        }
//                    });
//
//                    binding.delete.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            FirebaseDatabase.getInstance().getReference()
//                                    .child("chats")
//                                    .child(senderRoom)
//                                    .child("messages")
//                                    .child(message.getMessageId()).setValue(null);
//                            dialog.dismiss();
//                        }
//                    });
//
//                    binding.cancel.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            dialog.dismiss();
//                        }
//                    });
//
//                    dialog.show();

//                    return false;
//                }
//            });
        }
        else {
            ReceiverViewHolder viewHolder = (ReceiverViewHolder) holder;
            if (message.getMessage().contains("firebasestorage")) {
                viewHolder.binding.message.setText("1 File");
            } else {

                viewHolder.binding.message.setText(message.getMessage());
            }


//            viewHolder.binding.message.setOnTouchListener(new View.OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    popup.onTouch(v, event);
//                    return false;
//                }
//            });
//
//            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//                @Override
//                public boolean onLongClick(View v) {
//                    View view = LayoutInflater.from(context).inflate(R.layout.delete_dialog, null);
//                    DeleteDialogBinding binding = DeleteDialogBinding.bind(view);
//                    AlertDialog dialog = new AlertDialog.Builder(context)
//                            .setTitle("Sterge mesaj")
//                            .setView(binding.getRoot())
//                            .create();
//
//                    binding.everyone.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            message.setMessage("Acest mesaj este sters.");
//                            message.setFeeling(-1);
//                            FirebaseDatabase.getInstance().getReference()
//                                    .child("chats")
//                                    .child(senderRoom)
//                                    .child("messages")
//                                    .child(message.getMessageId()).setValue(message);
//
//                            FirebaseDatabase.getInstance().getReference()
//                                    .child("chats")
//                                    .child(receiverRoom)
//                                    .child("messages")
//                                    .child(message.getMessageId()).setValue(message);
//                            dialog.dismiss();
//                        }
//                    });
//
////dacă utilizatorul respinge mesajul, acesta va închide caseta de dialog
//
//                    binding.delete.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            FirebaseDatabase.getInstance().getReference()
//                                    .child("chats")
//                                    .child(senderRoom)
//                                    .child("messages")
//                                    .child(message.getMessageId()).setValue(null);
//                            dialog.dismiss();
//                        }
//                    });
//
//                    binding.cancel.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            dialog.dismiss();
//                        }
//                    });
//
//                    dialog.show();
//
//                    return false;
//                }
//            });

        }
    }


    @Override
    public int getItemCount() {
        return messages.size();
    }

    public class SentViewHolder extends RecyclerView.ViewHolder {

        ItemSentBinding binding;

        public SentViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemSentBinding.bind(itemView);
        }

    }

    public class ReceiverViewHolder extends RecyclerView.ViewHolder {

        ItemReceiveBinding binding;

        public ReceiverViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemReceiveBinding.bind(itemView);
        }
    }
}

