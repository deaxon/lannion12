class CreatePdfUploads < ActiveRecord::Migration
  def change
    create_table :pdf_uploads do |t|
      t.string :pdf
      t.references :attachable, :polymorphic => true

      t.timestamps
    end
  end

  def self.down
    drop_table :pdf_uploads
  end
end
