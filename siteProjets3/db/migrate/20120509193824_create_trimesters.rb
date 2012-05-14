class CreateTrimesters < ActiveRecord::Migration
  def change
    create_table :trimesters do |t|
      t.integer :student_id, :null => false
      t.integer :project_id
      t.string :semester

      t.timestamps
    end
  end
  
  def self.down
    drop_table :trimesters
  end
end
