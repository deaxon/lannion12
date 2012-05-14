class CreateStudents < ActiveRecord::Migration
  def change
    create_table :students do |t|
      t.string :studentName
      t.string :studentFirstName
      t.string :cip
      t.boolean :inactif

      t.timestamps
    end
  end

  def self.down
    drop_table :students
  end
end
